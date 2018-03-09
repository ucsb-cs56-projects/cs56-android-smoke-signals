package edu.ucsb.cs56.projects.androidapp.smokesignals.api.commands;

import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;

import edu.ucsb.cs56.projects.androidapp.smokesignals.api.Command;
import edu.ucsb.cs56.projects.androidapp.smokesignals.api.commands.validators.NArgValidator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ankushrayabhari on 11/4/17.
 */

public class ContactCommand extends NArgValidator implements Command {

    public ContactCommand() {
        super(1);
    }

    @Override
    public String getUsage() {
        return "//contact [query]";
    }

    public String execute(Context context, String[] args) {
        String query = args[0];

        if (query.length()<=2){
            return "Query is too short. Please provide a contact query at least of at 3 characters";
        }

        List<Contact> contacts = getContacts(context, query);
        if (contacts.isEmpty()) {
            return "No contact found matching " + query;
        }

        StringBuilder output = new StringBuilder("Possible Matches:\n");

        for (Contact contact : contacts) {
            output.append(contact);
        }

        return output.toString();
    }

    private List<Contact> getContacts(Context context, String query) {
        List<Contact> contacts = new ArrayList<>();

        query = "%"+query+"%";
        String where = ContactsContract.Data.MIMETYPE + " = ? AND " + ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME + " LIKE ?" ;

        String[] params = {ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE, query};

        String[] projectionQuery = {ContactsContract.CommonDataKinds.Phone.NUMBER, ContactsContract.CommonDataKinds.Email.DATA, ContactsContract.CommonDataKinds.StructuredName.GIVEN_NAME};

        try (Cursor c = context.getContentResolver().query(ContactsContract.Contacts.CONTENT_URI, projectionQuery, where, params, ContactsContract.CommonDataKinds.StructuredName.GIVEN_NAME)) {

            if (c == null || !c.moveToFirst()) {
                return contacts;
            }

            do {
                String number = c.getString(c.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                String name = c.getString(c.getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA));
                String email = c.getString(c.getColumnIndex(ContactsContract.CommonDataKinds.StructuredName.GIVEN_NAME));

                contacts.add(new Contact(number, name, email));

            } while (c.moveToNext());
        }
        return contacts;
    }

    private class Contact {
        private final String NUMBER, NAME, EMAIL;

        Contact(String number, String name, String email) {
            this.NUMBER = number;
            this.NAME = name;
            this.EMAIL = email;
        }

        @Override
        public String toString() {
            String str = "";
            if (!NUMBER.isEmpty()) {
                str += "Name: " + NAME + "\n";
                str += "Phone: " + NUMBER + "\n";
                if (!EMAIL.isEmpty()) {
                    str += "Email: " + EMAIL + "\n";
                }
                str += "-----\n";
            }

            return str;
        }

    }
}

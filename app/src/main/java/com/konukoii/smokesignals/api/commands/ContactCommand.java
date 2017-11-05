package com.konukoii.smokesignals.api.commands;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;

import com.konukoii.smokesignals.api.Command;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ankushrayabhari on 11/4/17.
 */

public class ContactCommand implements Command {

    /** Returns a list of user's contacts by query
     * @param context Context
     * @param args An array with a single element: The query
     * @return a list of user's contacts by query
     */
    public String execute(Context context, String[] args) {

        if (args.length != 1) {
            throw new RuntimeException("Expected 1 arg, got " + args.length);
        }

        String query = args[0];
        //DO NOT DELETE THIS. Believe me, I have seen what happens when you indiscriminatly query for 'a' and suddenly your phone is dumping all your contacts!
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

        query = "%"+query+"%"; //Super important for the SQL LIKE command (LIKE %ed% returns true to EDuardo, pEDro, etc. (also LIKE is not case sensitive!)
        String where = ContactsContract.Data.MIMETYPE + " = ? AND " +
                ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME + " LIKE ?" ;


        String[] params = {
                ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE,
                query
        };

        String[] projectionQuery = {
                ContactsContract.CommonDataKinds.Phone.NUMBER,
                ContactsContract.CommonDataKinds.Email.DATA,
                ContactsContract.CommonDataKinds.StructuredName.GIVEN_NAME
        };

        try (Cursor c = context.getContentResolver().query(
                ContactsContract.Contacts.CONTENT_URI,
                projectionQuery,
                where,
                params,
                ContactsContract.CommonDataKinds.StructuredName.GIVEN_NAME)) {


            if (c == null || !c.moveToFirst()) {
                return contacts;
            }

            do {
                int numberIndex = c.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
                int emailIndex = c.getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA);
                int nameIndex = c.getColumnIndex(ContactsContract.CommonDataKinds.StructuredName.GIVEN_NAME);

                String number = c.getString(numberIndex);
                String name = c.getString(nameIndex);
                String email = c.getString(emailIndex);

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

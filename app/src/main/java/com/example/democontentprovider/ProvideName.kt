package com.example.democontentprovider

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import android.util.Log


class ProvideName : ContentProvider() {


    override fun onCreate(): Boolean {
        return true
    }




    override fun query(
        uri: Uri,
        projection: Array<String>?,
        selection: String?,
        selectionArgs: Array<String>?,
        sortOrder: String?
    ): Cursor? {

            val context = context ?: return null
            val userDao= SampleAppDatabase.getInstance(context)?.userDao()
            val cursor: Cursor? = userDao?.selectAll()
            cursor?.setNotificationUri(context.contentResolver, uri)
           return cursor

    }




    override fun getType(uri: Uri): String? {
        Log.e("Iamgoot", uri.toString())
        return when (MATCHER.match(uri)) {

            CODE_COMPANYTM_DIR -> "vnd.android.cursor.dir/$AUTHORITY.abc"
            CODE_COMPANYTM_ITEM -> "vnd.android.cursor.item/$AUTHORITY.abc"
            else -> throw IllegalArgumentException("Unknown URI: $uri")
        }
    }

    override fun insert(uri: Uri, contentValues: ContentValues?): Uri? {
        throw IllegalStateException("Not allowed.")
    }

    override fun delete(uri: Uri, s: String?, strings: Array<String>?): Int {
        throw java.lang.IllegalStateException("Not allowed.")
    }

    override fun update(
        uri: Uri,
        contentValues: ContentValues?,
        s: String?,
        strings: Array<String>?
    ): Int {
        throw java.lang.IllegalStateException("Not allowed.")
    }

    companion object {

         val AUTHORITY = "com.example.democontentprovider.auto"


        /**The match code for some items in the companyTM table.  */
        private const val CODE_COMPANYTM_DIR= 1
        /** The match code for an item in the companyTM table.  */
        private const val CODE_COMPANYTM_ITEM = 2
        /** The URI matcher.  */
        private val MATCHER = UriMatcher(UriMatcher.NO_MATCH)

        init {
            MATCHER.addURI(
                AUTHORITY,
                "abc",
                CODE_COMPANYTM_DIR
            )
            MATCHER.addURI(
                AUTHORITY,
                "abc/*",
                CODE_COMPANYTM_ITEM
            )
        }
    }





//    content://com.demo.SampleContentProvider/abc


}
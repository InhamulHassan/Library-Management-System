package com.municipal.cmb.data;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.Toast;

import com.municipal.cmb.activity.R;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * SQL Helper will allow me to use all the sql query easily
 */

public class SQLiteHelper extends SQLiteOpenHelper {
    private Context context;

    public SQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.context = context;
    }

    //this method converts array to string to be put into db
    private static String convertArrayToString(String[] array) {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < array.length; i++) {
            str.append(array[i]);
            if (i < (array.length - 1)) {
                str.append(",");
            }
        }
        return str.toString();
    }

    //this method converts string to array to be put into db
    private static String[] convertStringToArray(String string) {
        String[] array;
        array = string.split(",");
        return array;
    }

    private void dataQuery(String sql) {
        SQLiteDatabase sqlDB = this.getWritableDatabase();
        sqlDB.execSQL(sql);
        sqlDB.close();
    }

    //bookId INTEGER PRIMARY KEY AUTOINCREMENT, title VARCHAR, ISBN VARCHAR, format VARCHAR, copies INTEGER, categoryId INTEGER
    public void insertBook(String bookTitle, String ISBN, String bookFormat, int copies, int category) {
        SQLiteDatabase sqlWritableDB = getWritableDatabase();
        String sql = "INSERT INTO bookTable VALUES (NULL, ?, ?, ?, ?, ?)";
        SQLiteStatement statement = sqlWritableDB.compileStatement(sql);
        statement.clearBindings();
        statement.bindString(1, bookTitle);
        statement.bindString(2, ISBN);
        statement.bindString(3, bookFormat);
        statement.bindDouble(4, copies);
        statement.bindDouble(5, category);
        statement.executeInsert();
        statement.close();
        sqlWritableDB.close();
        String sql1 = "SELECT seq FROM sqlite_sequence WHERE name=?";
        SQLiteDatabase sqlReadableDB = this.getReadableDatabase();
        SQLiteStatement statement1 = sqlReadableDB.compileStatement(sql1);
        statement1.execute();
        int last_insert_id = (int) statement1.simpleQueryForLong();
        statement1.close();
        sqlWritableDB.close();
        // TODO:: put the genre to the the table
    }

    public void updateBook(int bookId, String bookTitle, String ISBN, String bookFormat, int copies, int category) {
        SQLiteDatabase sqlDB = getWritableDatabase();
        String sql = "UPDATE bookTable SET title=?, ISBN=?, format=?, copies=?, categoryId=? WHERE bookId=?";
        SQLiteStatement statement = sqlDB.compileStatement(sql);
        statement.clearBindings();
        statement.bindString(1, bookTitle);
        statement.bindString(2, ISBN);
        statement.bindString(3, bookFormat);
        statement.bindDouble(4, copies);
        statement.bindDouble(5, category);
        statement.bindDouble(6, bookId);

        statement.executeUpdateDelete();
    }

    public void removeBook(int bookId) {
        SQLiteDatabase sqlDB = getWritableDatabase();
        String sql = "DELETE FROM bookTable WHERE bookId=?";
        SQLiteStatement statement = sqlDB.compileStatement(sql);
        statement.clearBindings();
        statement.bindDouble(1, bookId);
        statement.executeUpdateDelete();
        statement.close();
    }

    Cursor getDataCursor(String sql) {
        SQLiteDatabase sqlDB = getReadableDatabase();
        return sqlDB.rawQuery(sql, null);
    }

    /*long getCount(String tableName) {
        long count;
        SQLiteDatabase db = getReadableDatabase();
        count = DatabaseUtils.queryNumEntries(db, tableName);
        db.close();
        return count;
    }*/

    // bookGenreId INTEGER PRIMARY KEY AUTOINCREMENT, bookId INTEGER, genreId INTEGER
    public void insertBookGenre(String cartId, int productId, int productQuantity) {
        SQLiteDatabase sqlDB = getWritableDatabase();
        String sql = "INSERT INTO bookGenreTable VALUES (NULL, ?, ?, ?)";
        SQLiteStatement statement = sqlDB.compileStatement(sql);
        statement.clearBindings();
        statement.bindString(1, cartId);
        statement.bindDouble(2, productId);
        statement.bindDouble(3, productQuantity);

        statement.executeInsert();
    }

    public void removeBookGenre(int id) {
        SQLiteDatabase sqlDB = getWritableDatabase();
        String sql = "DELETE FROM bookGenreTable WHERE id=?";
        SQLiteStatement statement = sqlDB.compileStatement(sql);
        statement.clearBindings();
        statement.bindLong(1, id);
        statement.executeUpdateDelete();
    }

    void clearCartItem(int id, int productId) {
        SQLiteDatabase sqlDB = getWritableDatabase();
        String sql;
        sql = "DELETE FROM sqlite_sequence WHERE name='productCartTable';";
        SQLiteStatement DeleteStatement = sqlDB.compileStatement(sql);
        DeleteStatement.executeUpdateDelete();
        DeleteStatement.close();
        if (id == 1) {
            //this one will execute when the id value is equal to one, so
            sql = "DELETE FROM productCartTable WHERE productId=?;";
            SQLiteStatement statement = sqlDB.compileStatement(sql);
            statement.clearBindings();
            statement.bindDouble(1, productId);
            statement.executeUpdateDelete();
            statement.close();
            String updateSql = "UPDATE productCartTable SET id=(id-1);";
            SQLiteStatement statement2 = sqlDB.compileStatement(updateSql);
            statement2.clearBindings();
            statement2.executeUpdateDelete();
            statement2.close();
        } else {
            sql = "DELETE FROM productCartTable WHERE productId=?;";
            SQLiteStatement statement = sqlDB.compileStatement(sql);
            statement.clearBindings();
            statement.bindDouble(1, productId);
            statement.executeUpdateDelete();
            statement.close();
            String updateSql = "UPDATE productCartTable SET id=(id-1) WHERE id>=?;";
            SQLiteStatement statement2 = sqlDB.compileStatement(updateSql);
            statement2.clearBindings();
            statement2.bindDouble(1, id);
            statement2.executeUpdateDelete();
            statement2.close();
        }
    }

    int getCartCount(String cartId) {
        int count = 0;
        String sql = "SELECT COUNT(*) FROM productCartTable WHERE cartId=?";
        SQLiteDatabase sqlDB = this.getReadableDatabase();
        Cursor cursor = sqlDB.rawQuery(sql, new String[]{cartId});
        while (cursor.moveToNext()) {
            count = cursor.getInt(0);
        }
        cursor.close();
        sqlDB.close();
        return count;
    }

    String getCartId(int id) {
        String cartId = null;
        String sql = "SELECT cardId FROM productCartTable WHERE id=?";
        SQLiteDatabase sqlDB = this.getReadableDatabase();
        String idValue = String.valueOf(id);
        Cursor cursor = sqlDB.rawQuery(sql, new String[]{idValue});
        while (cursor.moveToNext()) {
            cartId = cursor.getString(0);
        }
        cursor.close();
        sqlDB.close();
        return cartId;
    }

    int getCartProductQuantity(int id) {
        int quantity = 0;
        String sql = "SELECT productQuantity FROM productCartTable WHERE id=?";
        SQLiteDatabase sqlDB = this.getReadableDatabase();
        String idValue = String.valueOf(id);
        Cursor cursor = sqlDB.rawQuery(sql, new String[]{idValue});
        while (cursor.moveToNext()) {
            quantity = cursor.getInt(0);
        }
        cursor.close();
        sqlDB.close();
        return quantity;
    }

    int getCartProductId(int id) {
        int productId = 0;
        String sql = "SELECT productId FROM productCartTable WHERE id=?";
        SQLiteDatabase sqlDB = this.getReadableDatabase();
        String idValue = String.valueOf(id);
        Cursor cursor = sqlDB.rawQuery(sql, new String[]{idValue});
        while (cursor.moveToNext()) {
            productId = cursor.getInt(0);
        }
        cursor.close();
        sqlDB.close();
        return productId;
    }

    public boolean doesMemberExists(String memberIdOrUsername) {
        String sql = "SELECT COUNT(*) FROM memberTable WHERE memberId=?";
        int count;
        SQLiteDatabase sqlReadableDB = this.getReadableDatabase();
        SQLiteStatement statement = sqlReadableDB.compileStatement(sql);
        statement.execute();
        count = (int) statement.simpleQueryForLong();
        statement.close();
        if (count <= 0) {
            String sql1 = "SELECT COUNT(*) FROM memberTable WHERE username=?";
            SQLiteStatement statement1 = sqlReadableDB.compileStatement(sql1);
            statement1.execute();
            count = (int) statement1.simpleQueryForLong();
            statement1.close();
        }
        sqlReadableDB.close();
        return count > 0;
    }

    /*ArrayList<CheckoutItems> getAllCheckoutItems(String cartId){
        ArrayList<CheckoutItems> checkoutItems = new ArrayList<>();
        SQLiteDatabase sqlDB = getReadableDatabase();
        String sql = "SELECT * FROM productCartTable WHERE cartId = ?";
        Cursor cursor = sqlDB.rawQuery(sql, new String[]{cartId});
        checkoutItems.clear();//this is to clear the view of previous search
        //we go through each element in the cursor (i.e our sql query result data)
        //we go through each element in the cursor (i.e our sql query result data)
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String[] idList = convertStringToArray(cursor.getString(2));
            String[] titleList = convertStringToArray(cursor.getString(3));
            String[] priceList = convertStringToArray(cursor.getString(4));
            String[] quantityList = convertStringToArray(cursor.getString(5));
            int priceTotal = cursor.getInt(6);
            //we add each of the table elements that we get from the cursor into the checkout array
            checkoutItems.add(new CheckoutItems(id, idList, titleList, priceList, quantityList, priceTotal));
        }
        cursor.close();
        return checkoutItems;
    }*/


    void createOrder(String orderID, String memberId, String[] idList, String[] quantityList, int netTotal, int orderStatus) {
        SQLiteDatabase sqlDB = getWritableDatabase();
        String sql = "INSERT INTO productOrderTable VALUES (?, ?, ?, ?, ?, ?)";
        SQLiteStatement statement = sqlDB.compileStatement(sql);
        statement.clearBindings();
        statement.bindString(1, orderID);
        statement.bindDouble(2, Integer.valueOf(memberId));
        statement.bindString(3, convertArrayToString(idList));
        statement.bindString(4, convertArrayToString(quantityList));
        statement.bindDouble(5, netTotal);
        statement.bindDouble(6, orderStatus); //0:ordered   1:confirmed

        statement.executeInsert();
    }

    //orderStatus 0:just-ordered 1:order-confirmed
    void confirmOrderStatus(String orderId) {
        SQLiteDatabase sqlDB = getWritableDatabase();
        String sql = "UPDATE productOrderTable SET orderStatus=1 WHERE orderId=?";
        SQLiteStatement statement = sqlDB.compileStatement(sql);
        statement.clearBindings();
        statement.bindString(1, orderId);
        statement.executeUpdateDelete();
    }

    int getOrderCount() {
        int count = 0;
        String sql = "SELECT COUNT(*) FROM productOrderTable WHERE orderStatus=0";
        SQLiteDatabase sqlDB = this.getReadableDatabase();
        Cursor cursor = sqlDB.rawQuery(sql, null);
        while (cursor.moveToNext()) {
            count = cursor.getInt(0);
        }
        cursor.close();
        sqlDB.close();
        return count;
    }

//    ArrayList<Orders> getAllOrderItems() {
//        ArrayList<Orders> ordersArrayList = new ArrayList<>();
//        SQLiteDatabase sqlDB = getReadableDatabase();
//        String sql = "SELECT * FROM productOrderTable WHERE orderStatus=0";
//        Cursor cursor = sqlDB.rawQuery(sql, null);
//        ordersArrayList.clear();//this is to clear the view of previous search
//        //we go through each element in the cursor (i.e our sql query result data)
//        //we go through each element in the cursor (i.e our sql query result data)
//        while (cursor.moveToNext()) {
//            String id = cursor.getString(0);
//            int memberId = cursor.getInt(1);
//            String[] idList = convertStringToArray(cursor.getString(2));
//            String[] quantityList = convertStringToArray(cursor.getString(3));
//            int priceNetTotal = cursor.getInt(4);
//            int orderStatus = cursor.getInt(5);
//            //we add each of the table elements that we get from the cursor into the checkout array
//            ordersArrayList.add(new Orders(id, memberId, idList, quantityList, priceNetTotal, orderStatus));
//        }
//        cursor.close();
//        return ordersArrayList;
//    }

    int getProductID(int productId) {
        int count = 0;
        String sql = "SELECT productID FROM ProductTable WHERE productId=?";
        SQLiteDatabase sqlDB = this.getReadableDatabase();
        Cursor cursor = sqlDB.rawQuery(sql, new String[]{String.valueOf(productId)});
        while (cursor.moveToNext()) {
            count = cursor.getInt(0);
        }
        cursor.close();
        return count;
    }

    String getProductTitle(int productId) {
        String name = null;
        String sql = "SELECT productTitle FROM ProductTable WHERE productId=?";
        SQLiteDatabase sqlDB = this.getReadableDatabase();
        Cursor cursor = sqlDB.rawQuery(sql, new String[]{String.valueOf(productId)});
        while (cursor.moveToNext()) {
            name = cursor.getString(0);
        }
        cursor.close();
        return name;
    }

    int getProductPrice(int productId) {
        int price = 0;
        String sql = "SELECT productPrice FROM ProductTable WHERE productId=?";
        SQLiteDatabase sqlDB = this.getReadableDatabase();
        Cursor cursor = sqlDB.rawQuery(sql, new String[]{String.valueOf(productId)});
        while (cursor.moveToNext()) {
            price = cursor.getInt(0);
        }
        cursor.close();
        return price;
    }

    void registerUser(String firstName, String lastName, String mobileNumber, String email, String password) {
        SQLiteDatabase sqlDB = getWritableDatabase();
        String sql = "INSERT INTO userTable VALUES (NULL, ?, ?, ?, ?, ?)";
        SQLiteStatement statement = sqlDB.compileStatement(sql);
        statement.clearBindings();
        statement.bindString(1, firstName);
        statement.bindString(2, lastName);
        statement.bindString(3, mobileNumber);
        statement.bindString(4, email);
        statement.bindString(5, password);

        statement.executeInsert();
    }

    //this method gets the memberId/username and password and checks it against the db and
    //returns true if the email is present and the password matches, it return false if
    // the email is not there in db or if the password doesn't match


    //this method checks if the supplied memberId is in the db
    public boolean checkMemberID(String memberId) {
        String sql = "SELECT COUNT(*) FROM memberTable WHERE memberId=?";
        SQLiteDatabase sqlReadableDB = this.getReadableDatabase();
        SQLiteStatement statement = sqlReadableDB.compileStatement(sql);
        statement.bindString(1, memberId);
        statement.execute();
        return statement.simpleQueryForLong() > 0;
    }

    //this method checks if the supplied email is in the db
    public boolean checkUsername(String username) {
        String sql = "SELECT COUNT(*) FROM memberTable WHERE username=?";
        SQLiteDatabase sqlReadableDB = this.getReadableDatabase();
        SQLiteStatement statement = sqlReadableDB.compileStatement(sql);
        statement.bindString(1, username);
        statement.execute();
        return statement.simpleQueryForLong() > 0;
    }

    public boolean checkPassword(String email, String password) {
        String sql = "SELECT userPassword FROM userTable WHERE userEmail=?";
        SQLiteDatabase sqlDB = this.getReadableDatabase();
        Cursor cursor = sqlDB.rawQuery(sql, new String[]{email});
        boolean isValid = false;
        while (cursor.moveToNext()) {
            String pass = cursor.getString(0);
            isValid = password.equals(pass);
        }
        cursor.close();
        return isValid;
    }

    int getUserId(String email) {
        String sql = "SELECT userId FROM userTable WHERE userEmail=?";
        SQLiteDatabase sqlDB = this.getReadableDatabase();
        Cursor cursor = sqlDB.rawQuery(sql, new String[]{email});
        int userId = 0;
        while (cursor.moveToNext()) {
            userId = cursor.getInt(0);
        }
        cursor.close();
        return userId;
    }

    byte[] getProductImage(int productId) {
        byte[] imgByte = null;
        String sql = "SELECT productImage FROM ProductTable WHERE productId=?";
        SQLiteDatabase sqlDB = this.getReadableDatabase();
        Cursor cursor = sqlDB.rawQuery(sql, new String[]{String.valueOf(productId)});
        while (cursor.moveToNext()) {
            imgByte = cursor.getBlob(0);
        }
        cursor.close();
        return imgByte;
    }

//    ArrayList<Products> getAllProductData(int productId) {
//        ArrayList<Products> ProductList = new ArrayList<>();
//        SQLiteDatabase sqlDB = getReadableDatabase();
//        String sql = "SELECT * FROM productTable WHERE productId=?";
//        Cursor cursor = sqlDB.rawQuery(sql, new String[]{String.valueOf(productId)});
//
//        //we go through each element in the cursor (i.e our sql query result data)
//        while (cursor.moveToNext()) {
//            int id = cursor.getInt(0);
//            String title = cursor.getString(1);
//            String category = cursor.getString(2);
//            int price = cursor.getInt(3);
//            String description = cursor.getString(4);
//            byte[] image = cursor.getBlob(5);
//
//            //we add each of the table elements that we get from the cursor into the product array
//            ProductList.add(new Products(id, title, category, price, description, image));
//        }
//        cursor.close();
//        return ProductList;
//    }
//
//    ArrayList<Products> getAllData() {
//        ArrayList<Products> ProductList = new ArrayList<>();
//        SQLiteDatabase sqlDB = getReadableDatabase();
//        String sql = "SELECT * FROM productTable";
//        Cursor cursor = sqlDB.rawQuery(sql, null);
//        ProductList.clear();//this is to clear the view of previous search
//        //we go through each element in the cursor (i.e our sql query result data)
//        while (cursor.moveToNext()) {
//            int id = cursor.getInt(0);
//            String title = cursor.getString(1);
//            String category = cursor.getString(2);
//            int price = cursor.getInt(3);
//            String description = cursor.getString(4);
//            byte[] image = cursor.getBlob(5);
//            //we add each of the table elements that we get from the cursor into the product array
//            ProductList.add(new Products(id, title, category, price, description, image));
//        }
//        cursor.close();
//        return ProductList;
//    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        /*
        * when the database is created for the first time or when getWritableDatabase() or getReadableDatabase()
        * is called this method runs. We can use this method to initialize the tables in this database
        */
        dataQuery("CREATE TABLE IF NOT EXISTS categoryTable (categoryId INTEGER PRIMARY KEY AUTOINCREMENT, category VARCHAR)");
        dataQuery("CREATE TABLE IF NOT EXISTS genreTable (genreId INTEGER PRIMARY KEY AUTOINCREMENT, genre VARCHAR, alias VARCHAR DEFAULT NULL, summary VARCHAR DEFAULT NULL)");
        dataQuery("CREATE TABLE IF NOT EXISTS authorTable (authorId INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR, summary VARCHAR DEFAULT NULL)");
        dataQuery("CREATE TABLE IF NOT EXISTS bookTable (bookId INTEGER PRIMARY KEY AUTOINCREMENT, title VARCHAR, ISBN VARCHAR, format VARCHAR, copies INTEGER, categoryId INTEGER, FOREIGN KEY (categoryId) REFERENCES categoryTable (categoryId) ON UPDATE CASCADE ON DELETE NO ACTION)");
        dataQuery("CREATE TABLE IF NOT EXISTS copyTable (copyId INTEGER PRIMARY KEY AUTOINCREMENT, format VARCHAR, shelfId VARCHAR, bookId INTEGER, availability INTEGER, FOREIGN KEY (bookId) REFERENCES bookTable (bookId) ON UPDATE CASCADE ON DELETE NULL)");
        dataQuery("CREATE TABLE IF NOT EXISTS bookAuthorTable (bookAuthorId INTEGER PRIMARY KEY AUTOINCREMENT, authorId INTEGER, bookId INTEGER, FOREIGN KEY (bookId) REFERENCES bookTable (bookId) ON UPDATE CASCADE ON DELETE SET NULL, FOREIGN KEY (authorId) REFERENCES authorTable (authorId) ON UPDATE CASCADE ON DELETE SET NULL)");
        dataQuery("CREATE TABLE IF NOT EXISTS bookGenreTable (bookGenreId INTEGER PRIMARY KEY AUTOINCREMENT, bookId INTEGER, genreId INTEGER, FOREIGN KEY (bookId) REFERENCES bookTable (bookId) ON UPDATE CASCADE ON DELETE NULL, FOREIGN KEY (genreId) REFERENCES genreTable (genreId) ON UPDATE CASCADE ON DELETE NULL)");
        dataQuery("CREATE TABLE IF NOT EXISTS memberTable (memberId VARCHAR PRIMARY KEY, username VARCHAR, password VARCHAR, joinDate DATE, expirationDate DATE)");
        dataQuery("CREATE TABLE IF NOT EXISTS issueTable (issueId INTEGER PRIMARY KEY AUTOINCREMENT, issuedDate DATE, returnDate DATE, firstBook INTEGER, secondBook INTEGER, FOREIGN KEY (firstBook) REFERENCES copyTable (copyId) ON UPDATE CASCADE ON DELETE NULL, FOREIGN KEY (secondBook) REFERENCES copyTable (copyId) ON UPDATE CASCADE ON DELETE NULL)");
        populateGenreToTable();
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        /*
        * when the database is update, all the existing tables in the database is dropped,
        * this is done in order for the onCreate method to be called, thus the tables can
        * be created again
        */
        dataQuery("DROP TABLE IF EXISTS categoryTable");
        dataQuery("DROP TABLE IF EXISTS genreTable");
        dataQuery("DROP TABLE IF EXISTS authorTable");
        dataQuery("DROP TABLE IF EXISTS bookTable");
        dataQuery("DROP TABLE IF EXISTS copyTable");
        dataQuery("DROP TABLE IF EXISTS bookAuthorTable");
        dataQuery("DROP TABLE IF EXISTS bookGenreTable");
        dataQuery("DROP TABLE IF EXISTS memberTable");
        dataQuery("DROP TABLE IF EXISTS issueTable");
    }

    // genreId INTEGER PRIMARY KEY AUTOINCREMENT, genre VARCHAR, alias VARCHAR DEFAULT NULL, summary VARCHAR DEFAULT NULL
    private void populateGenreToTable() {
        String insertQuery = "INSERT INTO genreTable VALUES (?, NULL, NULL)";
        String array[] = context.getResources().getStringArray(R.array.book_genre_list);
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            db.beginTransaction(); //starts a transaction
            SQLiteStatement statement = db.compileStatement(insertQuery); //compiles the prepares statement
            for (String item : array) {
                statement.clearBindings();
                statement.bindString(1, item);
                statement.executeInsert();
            } //the foreach loop binds the data to the compiled statement and executes it
            db.setTransactionSuccessful();//if all the fields in the array get inserted into table the transaction is successful, and is committed
        } catch (Exception ex) {
            Log.e("SQL_ERROR", Arrays.toString(ex.getStackTrace())); //prints the error log stack trace to the log
        } finally {
            db.endTransaction();
        }

    }
}

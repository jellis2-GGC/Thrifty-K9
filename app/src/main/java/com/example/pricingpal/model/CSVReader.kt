package com.example.pricingpal.model

import android.content.Context
import java.io.FileNotFoundException
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.util.Scanner

/**
 * Class: CSVReader
 * @author Julian Ellis
 * @version 2.1
 * @written 9/24/2023
 * This class extends AppCompatActivity and holds a function to read a CSV file
 *   from an assets folder.
 *
 */
class CSVReader {

    /**
     * readFile
     * This function reads a csv file into an ArrayList of Item objects. This
     *  is a composable function.
     * @param fileName The name of the file to be read
     * @param c Context of the calling program (In MainActivity.kt use "this" keyword)
     * @return itemList The list of Item objects generated by the function
     */

    fun readFile(c: Context, fileName: String): List<Item> {

        var itemList = ArrayList<Item>()

        try {
            //Point the input stream at the assets folder and open the file
            val inputStream: InputStream = c.assets.open(fileName)
            val n = Scanner(InputStreamReader(inputStream))
            itemList = ArrayList<Item>()
            //Skip the first line if column headers are present, otherwise comment out
            //NOTE: testdata.csv has no column headers
            //val skipColumnHeader = n.nextLine()
            //Read file and put into Item objects list
            while (n.hasNext()) {
                val line = n.nextLine()
                val row = line.split(",")
                //This error should resolve itself when updated item class is added
                val item = Item(row[0], row[1], row[2].toDouble())
                itemList.add(item)
            }
            n.close()
        } catch (e: FileNotFoundException) {
            println("The file was not found. Check file name or path")
        } catch (e: IOException) {
            println("There was an error reading the file")
        } catch (e: NullPointerException) {
            println("There is nothing in the list")
        }
        //Return the list of items generated in the function
        return itemList
    }


    //Original algorithm
    /*fun readFile(): List<Item> {
        val fileName = "testdata.csv"
        val inputStream: InputStream = assets.open(fileName)
        val n = Scanner(InputStreamReader(inputStream))

        val itemList = ArrayList<Item>()
        while(n.hasNext()) {
            val line = n.nextLine()
            val row = line.split(",")
            val item = Item(row[0], row[1], row[2].toDouble())
            itemList.add(item)
        }
        n.close()
        return itemList
    }*/
}


package com.example.pricingpal.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.pricingpal.R
import com.example.pricingpal.model.Category

@Composable
fun CategoryList (categories: HashMap<String, Category>, navController: NavController, padding: PaddingValues){
    LazyColumn(
        //aligns the categories within the center
        modifier = Modifier
            .fillMaxSize()
            .padding(padding),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        // takes each category card and put into a list
        for(category : Category in categories.values) {
            item { CategoryCard(category = category, navController)}
        }

    }
}

@Composable
//puts the category name into a card view
fun CategoryCard(category: Category, navController: NavController){
    Card(
        modifier = Modifier
            //Makes the card clickable, and when clicked navigates to the ItemList passing along the category name as a String
            .clickable(onClick = { navController.navigate(Screen.ItemList.withArgs(category.category)) })

            // padding around the card
            .padding(15.dp)
            .fillMaxWidth()
            .border(
                // puts a border around the card
                border = BorderStroke(4.dp, color = colorResource(id = R.color.deep_blue)),
                // shapes the card
                shape = RectangleShape
            ),
        // puts a shadow under the card to make it pop out
        elevation = CardDefaults.cardElevation(8.dp),
    ) {
        // Column to display image and category name on card
        Column(modifier = Modifier
            // the size of the box
            .background(color = colorResource(id = R.color.pale_blue))
        ){
            //Here is where the code to display image would be. Current image is a placeholder
            Image(
                painter = painterResource(id =R.drawable.accessories),
                contentDescription = "Accessories image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentScale = ContentScale.Crop,
                alpha = 0.8F
            )
            // Displays category name
            Text(
                // takes the text from the category variable
                text = category.category,
                // changes the size of the font
                fontSize = 30.sp,
                // allows for the font to be in bold
                fontWeight = FontWeight.Bold,
                //change the color of the text
                color = Color.Black,
                modifier = Modifier
                    .padding(10.dp)
                    .align(alignment = Alignment.CenterHorizontally)
            )
        }
    }
}
package com.joel.profile.account

import android.net.Uri
import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import kotlinx.coroutines.launch


// should use room
// card that holds diets, allergies, nutrients, on click launches a bottom sheet
// holds image and user name
// should have a dialog for image and name input
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AccountsScreen(
    viewModel: AccountViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
){

    val scope = rememberCoroutineScope()
    val bottomSheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = false
    )

    val items by viewModel.preferenceListItems.collectAsState(initial = emptyList())

    BackHandler(bottomSheetState.isVisible) {
        scope.launch { bottomSheetState.hide() }
    }


    Scaffold(
        topBar = {
            TopAppBar(
                title = { /*TODO*/ },
                actions = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(imageVector = Icons.Default.Check, contentDescription = null)
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(paddingValues)
        ) {
            EditUserImageAndName(
                name = viewModel.userName,
                onNameChange = { name ->
                    viewModel.updateUserDetails(AccountUiEvents.SelectUserName(name))
                },
                imagePlaceHolder = viewModel.placeHolderImage,
                profileUri = viewModel.profileUrl,
                onImageChange = { image ->
                    viewModel.updateUserDetails(AccountUiEvents.SelectProfileImage(image))
                }
            )
            PreferenceList(
                onPreferenceClick = { item ->
                    scope.launch {
                        items.contains(item)
                        bottomSheetState.show()
                    }
                },
                items = items,
                viewModel = viewModel,
                onClose = {
                    scope.launch {
                        bottomSheetState.hide()
                    }
                },
                bottomSheetVisibility = bottomSheetState.isVisible
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PreferenceList(
    items : List<UserPreferenceItems>,
    onPreferenceClick: (UserPreferenceItems) -> Unit,
    bottomSheetVisibility : Boolean,
    onClose: () -> Unit,
    viewModel: AccountViewModel
){

    LazyColumn {
        items(items){ item ->
            PreferenceItems(
                onLaunchBottomSheet = onPreferenceClick,
                item = item,
                bottomSheetVisibility = bottomSheetVisibility,
                onClose = { onClose() },
                viewModel = viewModel
            )

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PreferenceItems(
    onLaunchBottomSheet : (UserPreferenceItems) -> Unit,
    item: UserPreferenceItems,
    bottomSheetVisibility : Boolean,
    onClose: () -> Unit,
    viewModel: AccountViewModel
){
    Card(
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.elevatedCardElevation(),
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onLaunchBottomSheet(item)
            }
            .background(MaterialTheme.colorScheme.background)
            .padding(vertical = 8.dp, horizontal = 16.dp)
            .height(50.dp)
    ) {
        Text(
            text = item.title,
            modifier = Modifier
                .padding(horizontal = 10.dp, vertical = 13.dp)
        )
    }

    if (bottomSheetVisibility){
        ModalBottomSheet(
            onDismissRequest = { onClose() }) {
            BottomSheetItems(
                possiblePreferences = item.list,
                onChipsSelected = { chipText ->
                    when (item.list) {
                        viewModel.dietList -> viewModel.updateUserDetails(AccountUiEvents.SelectDiet(chipText))
                        viewModel.allergiesList -> viewModel.updateUserDetails(AccountUiEvents.SelectAllergies(chipText))
                        viewModel.nutrientsList -> viewModel.updateUserDetails(AccountUiEvents.SelectNutrition(chipText))
                    }
                },
                onClose = { onClose() },
                selectedPreferences = item.selectedAnswers
            )
        }
    }
}


@Composable
fun BottomSheetItems(
    possiblePreferences: List<String>,
    selectedPreferences: List<String>,
    onChipsSelected : (String) -> Unit,
    onClose: () -> Unit,
){

   Column{

       Box(
           contentAlignment = Alignment.TopEnd,
           modifier = Modifier
               .fillMaxWidth()
       ) {
           IconButton(onClick = { onClose() }) {
               Icon(imageVector = Icons.Default.Check, contentDescription = null)
           }
       }
       LazyVerticalGrid(
           columns = GridCells.Fixed(2),
           modifier = Modifier
               .height(450.dp)
       ){
           items(possiblePreferences){chipText ->
               val selected =  chipText in selectedPreferences
               AssistChip(
                   modifier = Modifier
                       .padding(8.dp)
                       .selectable(
                           selected = selected
                       ) {
                           onChipsSelected(chipText)
                       },
                   label = {
                       Text(
                           chipText,
                       )
                   },
                   colors = AssistChipDefaults.assistChipColors(
                       containerColor = if (selected) Color.Yellow else MaterialTheme.colorScheme.onPrimary,
                   ),
                   onClick = {
                       onChipsSelected(chipText)
                   }
               )
           }
       }
   }
}


@Composable
fun EditUserImageAndName(
    name : String,
    onNameChange : (String) -> Unit,
    imagePlaceHolder : Any?,
    profileUri : Uri?,
    onImageChange : (Uri) -> Unit,
) {


    val profilePhotoPicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia()
    ) {
        if (it != null) {
            Log.d("PhotoPicker", "Selected URI: $it")
            onImageChange(it)
        } else {
            Log.d("PhotoPicker", "No media selected")
        }
    }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (profileUri != null){
            Card(
                shape = RoundedCornerShape(50),
                elevation = CardDefaults.elevatedCardElevation(
                    defaultElevation = 0.dp
                ),
                modifier = Modifier
                    .size(200.dp)
            ){
                AsyncImage(
                    modifier = Modifier
                        .size(200.dp)
                        .clickable {
                            profilePhotoPicker.launch(
                                PickVisualMediaRequest(
                                    ActivityResultContracts.PickVisualMedia.ImageOnly
                                )
                            )
                        },
                    model = ImageRequest.Builder(LocalContext.current).data(profileUri)
                        .crossfade(enable = true).build(),
                    contentDescription = "Avatar Image",
                    contentScale = ContentScale.FillBounds,
                )
            }
        } else {
            Card(
                shape = RoundedCornerShape(50),
                elevation = CardDefaults.elevatedCardElevation(
                    defaultElevation = 0.dp
                ),
                modifier = Modifier
                    .size(200.dp)
            ){
                AsyncImage(
                    modifier = Modifier
                        .size(200.dp)
                        .clickable {
                            profilePhotoPicker.launch(
                                PickVisualMediaRequest(
                                    ActivityResultContracts.PickVisualMedia.ImageOnly
                                )
                            )
                        },
                    model = ImageRequest.Builder(LocalContext.current).data(imagePlaceHolder)
                        .crossfade(enable = true).build(),
                    contentDescription = "Avatar Image",
                    contentScale = ContentScale.Crop,
                )
            }
        }

        OutlinedTextField(
            value = name,
            onValueChange = onNameChange,
            maxLines = 1,
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier
                .padding(vertical = 13.dp, horizontal = 16.dp)
                .fillMaxWidth(),
            label = {
                Text("Name")
            }
        )
    }
}


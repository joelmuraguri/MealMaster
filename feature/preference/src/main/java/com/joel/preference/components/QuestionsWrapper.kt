package com.joel.preference.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.joel.preference.R

@Composable
fun UserNameQuestion(
    userName : String,
    onNameChange: (String) -> Unit,
    modifier: Modifier = Modifier
){

    NameQuestion(
        title = R.string.name_question,
        name = userName,
        onNameChange = onNameChange,
        modifier = modifier
    )

}

@Composable
fun DietQuestion(
    selectedAnswers: List<String>,
    onOptionSelected: ( String) -> Unit,
    modifier: Modifier = Modifier,
    possibleAnswers: List<String>

){

    PreferenceQuestion(
        title = R.string.diet_question,
        possibleAnswers = possibleAnswers,
        selectedAnswers = selectedAnswers,
        onChipsSelected = onOptionSelected,
        modifier = modifier
    )

}

@Composable
fun AllergiesQuestion(
    selectedAnswers: List<String>,
    onOptionSelected: (String) -> Unit,
    modifier: Modifier = Modifier,
    possibleAnswers: List<String>

){

    PreferenceQuestion(
        title = R.string.allergies_question,
        possibleAnswers = possibleAnswers,
        selectedAnswers = selectedAnswers,
        onChipsSelected = onOptionSelected,
        modifier = modifier
    )
}

@Composable
fun NutrientsQuestion(
    selectedAnswers: List<String>,
    onOptionSelected: (String) -> Unit,
    modifier: Modifier = Modifier,
    possibleAnswers: List<String>
){

    PreferenceQuestion(
        title = R.string.nutrients_question,
        possibleAnswers = possibleAnswers,
        selectedAnswers = selectedAnswers,
        onChipsSelected = onOptionSelected,
        modifier = modifier
    )

}

@Composable
fun NameQuestion(
    title : Int,
    name : String,
    onNameChange : (String) -> Unit,
    modifier: Modifier = Modifier
){

    QuestionWrapper(
        titleResourceId = title,
        modifier = modifier
    ) {
        OutlinedTextField(
            value = name,
            onValueChange = {
                onNameChange(it)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        )
    }
}

@Composable
fun PreferenceQuestion(
    title: Int,
    possibleAnswers: List<String>,
    selectedAnswers: List<String>,
    onChipsSelected : (String) -> Unit,
    modifier: Modifier = Modifier,
){

    QuestionWrapper(
        titleResourceId = title,
        modifier = modifier,
    ) {
        LazyVerticalGrid(columns = GridCells.Fixed(2)){
            items(possibleAnswers){chipText ->
                val selected =  chipText in selectedAnswers
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
fun QuestionWrapper(
    @StringRes titleResourceId: Int,
    modifier: Modifier = Modifier,
    @StringRes directionsResourceId: Int? = null,
    content: @Composable () -> Unit,
){

    Column(
        modifier = modifier
            .padding(horizontal = 16.dp)
    ) {
        Spacer(Modifier.height(32.dp))
        QuestionTitle(titleResourceId)
        directionsResourceId?.let {
            Spacer(Modifier.height(18.dp))
            QuestionDirections(it)
        }
        Spacer(Modifier.height(18.dp))

        content()
    }
}

@Composable
private fun QuestionTitle(
    @StringRes title: Int,
    modifier: Modifier = Modifier,
) {
    Text(
        text = stringResource(id = title),
        style = MaterialTheme.typography.titleMedium,
        color = MaterialTheme.colorScheme.onSurface.copy(),
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 24.dp, horizontal = 16.dp)
    )
}

@Composable
private fun QuestionDirections(
    @StringRes directionsResourceId: Int,
    modifier: Modifier = Modifier,
) {
    Text(
        text = stringResource(id = directionsResourceId),
        color = MaterialTheme.colorScheme.onSurface
            .copy(),
        style = MaterialTheme.typography.bodySmall,
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
    )
}
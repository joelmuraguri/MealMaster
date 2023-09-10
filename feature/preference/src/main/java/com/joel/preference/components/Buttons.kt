package com.joel.preference.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.joel.preference.R

@Composable
fun PreferenceBottomBar(
    showPreviousButton : Boolean,
    showFinishButton : Boolean,
    onPreviousPressed : () -> Unit,
    onFinishPressed : () -> Unit,
    onNextPressed : () -> Unit,
    isNextButtonEnabled : Boolean
){

    Surface(
        modifier = Modifier
            .fillMaxWidth(),
        shadowElevation = 8.dp
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 20.dp)
        ) {
            if (showPreviousButton){
                OutlinedButton(
                    onClick = { onPreviousPressed() },
                    modifier = Modifier
                        .weight(1f)
                        .height(50.dp)
                ) {
                    Text(text = stringResource(id = R.string.previous_button))
                }
            }
            if (showFinishButton){
                Button(
                    onClick = { onFinishPressed() },
                    modifier = Modifier
                        .weight(1f)
                        .height(50.dp),
                    enabled = isNextButtonEnabled
                ) {
                    Text(text = stringResource(id = R.string.finish_button))
                }
            } else {
                Button(
                    onClick = { onNextPressed() },
                    modifier = Modifier
                        .weight(1f)
                        .height(50.dp),
                    enabled = isNextButtonEnabled
                ) {
                    Text(text = stringResource(id = R.string.next_button))
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PreferenceTopBar(
    onClosePressed : () -> Unit,
    questionIndex : Int,
    totalQuestions : Int,
){

    Column(
        modifier = Modifier
            .fillMaxWidth()
    ){
        CenterAlignedTopAppBar(
            title = {
                TopAppBarTitle(
                    questionIndex = questionIndex,
                    totalQuestionsCount = totalQuestions)
            },
            actions = {
                IconButton(onClick = { onClosePressed() }) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = stringResource(id = R.string.close_icon)
                    )
                }
            }
        )
        val animatedFloatProgress by animateFloatAsState(
            targetValue = (questionIndex + 1) / totalQuestions.toFloat(),
            animationSpec = ProgressIndicatorDefaults.ProgressAnimationSpec, label = ""
        )
        LinearProgressIndicator(
            progress = animatedFloatProgress,
            trackColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2F),
            color = Color.Yellow,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        )
    }
}

@Composable
private fun TopAppBarTitle(
    questionIndex: Int,
    totalQuestionsCount: Int,
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier) {
        Text(
            text = (questionIndex + 1).toString(),
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onSurface.copy()
        )
        Text(
            text = stringResource(R.string.question_count, totalQuestionsCount),
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.38f)
        )
    }
}


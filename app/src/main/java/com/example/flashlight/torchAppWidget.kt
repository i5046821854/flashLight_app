package com.example.flashlight

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews

/**
 * Implementation of App Widget functionality.
 */
class torchAppWidget : AppWidgetProvider() {  //appwidgetProvide : 브로드캐스터 리시버 클래스
    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        // There may be multiple widgets active, so update all of them
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId)
        }
    }

    override fun onEnabled(context: Context) { //위젯이 처음 생성 될 때
        // Enter relevant functionality for when the first widget is created
    }

    override fun onDisabled(context: Context) { //여러개의 위젯이 있을 경우 마지막 위젯이 제거될 때
        // Enter relevant functionality for when the last widget is disabled
    }
}

internal fun updateAppWidget( //위젯을 업데이트 할 때
    context: Context,
    appWidgetManager: AppWidgetManager,
    appWidgetId: Int
) {
    val widgetText = context.getString(R.string.appwidget_text)
    // Construct the RemoteViews object
    val views = RemoteViews(context.packageName, R.layout.torch_app_widget)
    views.setTextViewText(R.id.appwidget_text, widgetText)

    //클릭하면 torchservice 시작
    val intent = Intent(context, torchService::class.java)
    val pendingIntent = PendingIntent.getService(context, 0, intent, 0)
    views.setOnClickPendingIntent(R.id.appWidget_layout,pendingIntent)

    // Instruct the widget manager to update the widget
    appWidgetManager.updateAppWidget(appWidgetId, views)
}
package org.toy.tiltsensor

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.hardware.SensorEvent
import android.view.View

class TiltView(context: Context?) : View(context) {
    private val greenPaint: Paint = Paint()
    private val blackPaint: Paint = Paint()

    private var cX: Float = 0f
    private var cY: Float = 0f
    private var xCoord: Float = 0f
    private var yCoord: Float = 0f

    init {
        greenPaint.color = Color.GREEN
        blackPaint.style = Paint.Style.STROKE
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        // 화면의 크기가 결정되면서 호출될 것.
        cX = w / 2f
        cY = h / 2f
    }

    override fun onDraw(canvas: Canvas?) {
        // 위치 테두리 그리기
        canvas?.drawCircle(cX, cY, 100f, blackPaint)
        // 녹색 공 그리기
        canvas?.drawCircle(cX + xCoord, cY + yCoord, 100f, greenPaint)
        canvas?.drawLine(cX - 20f, cY, cX + 20f, cY, blackPaint)
        canvas?.drawLine(cX, cY - 20f, cX, cY + 20f, blackPaint)
    }

    fun onSensorEvent(event: SensorEvent) {
        yCoord = event.values[0] * 20 // 가로 고정시켰기 때문에,
        xCoord = event.values[1] * 20 // x축, y축이 달라졌다.

        // 뷰의 onDraw()를 다시 호출해주는 메소드
        invalidate()
    }
}
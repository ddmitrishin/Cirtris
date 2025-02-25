package com.example.cirtris.ui.theme

import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.MotionEvent
import android.view.SurfaceHolder
import android.view.SurfaceView
import com.example.cirtris.game.Direction
import com.example.cirtris.game.GameManager

class CirtrisView(context: Context) : SurfaceView(context), Runnable {
    private val gameManager = GameManager()
    private var thread: Thread? = null
    private var canvasWidth = 0
    private var canvasHeight = 0
    private var isRunning = false

    init {
        holder.addCallback(object : SurfaceHolder.Callback {
            override fun surfaceCreated(holder: SurfaceHolder) {
                resume()
            }

            override fun surfaceDestroyed(holder: SurfaceHolder) {
                pause()
            }

            override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {
                // Обновление размеров
            }
        })
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        canvasWidth = w
        canvasHeight = h
    }

    override fun run() {
        while (isRunning) {
            if (!holder.surface.isValid) continue
            update()
            draw()
            Thread.sleep((500 / gameManager.level).toLong())
        }
    }

    fun pause() {
        isRunning = false
        try {
            thread?.join()
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }

    fun resume() {
        isRunning = true
        thread = Thread(this)
        thread?.start()
    }

    private fun draw() {
        if (holder.surface.isValid) {
            val canvas = holder.lockCanvas()
            canvas.drawColor(Color.WHITE) // Базовый фон
            // Отрисовка фигур
            holder.unlockCanvasAndPost(canvas)
        }
        Log.d("Cirtris", "Drawing canvas")
    }

    private fun update() {
        gameManager.update()
        Log.d("Cirtris", "Updating...")
    }

    private var touchStartX = 0f
    private var touchStartY = 0f

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                touchStartX = event.x
                touchStartY = event.y
            }

            MotionEvent.ACTION_UP -> {
                val dx = event.x - touchStartX
                val dy = event.y - touchStartY
                if (Math.abs(dx) > Math.abs(dy)) {
                    // Горизонтальный свайп
                    if (dx > 0) gameManager.moveShape(Direction.RIGHT)
                    else gameManager.moveShape(Direction.LEFT)
                } else {
                    // Вертикальный свайп
                    if (dy > 0) gameManager.moveShape(Direction.INWARD)
                }
            }
        }
        return true
    }

}
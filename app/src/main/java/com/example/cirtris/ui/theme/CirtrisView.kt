package com.example.cirtris.ui.theme

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.SurfaceHolder
import android.view.SurfaceView
import com.example.cirtris.game.GameField
import com.example.cirtris.game.GameManager

class CirtrisView(
    context: Context,
    attrs: AttributeSet?
) : SurfaceView(context, attrs), Runnable {
    private val gameField = GameField(...)
    private var isRunning = true
    private var thread: Thread? = null
    private val gameManager = GameManager()

    init {
        holder.addCallback(object : SurfaceHolder.Callback {
            override fun surfaceCreated(holder: SurfaceHolder) {
                resume()
            }

            override fun surfaceDestroyed(holder: SurfaceHolder) {
                pause()
            }

            override fun surfaceChanged(
                holder: SurfaceHolder,
                format: Int,
                width: Int,
                height: Int
            ) {
                // Handle surface changes if needed
            }
        })
    }

    override fun run() {
        while (isRunning) {
            if (!gameManager.gameOver) {
                gameManager.update()
            }
            draw()
            Thread.sleep(50) // Delay to control game speed
        }
    }

    private fun draw() {
        if (holder.surface.isValid) {
            val canvas = holder.lockCanvas()
            canvas.drawColor(Color.WHITE) // Base background
            holder.unlockCanvasAndPost(canvas)
        }
    }

    fun resume() {
        isRunning = true
        thread = Thread(this)
        thread?.start()
    }

    fun pause() {
        isRunning = false
        try {
            thread?.join()
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }
}
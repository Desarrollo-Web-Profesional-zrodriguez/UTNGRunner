package mx.utng.utngrunner.presentation.game

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.DrawScope
import mx.utng.utngrunner.domain.model.*
import kotlin.math.sin

/** GameRenderer: SOLO dibuja. No toca la lógica de juego. */
object GameRenderer {
 
    fun draw(drawScope: DrawScope, state: GameState, frame: Long) {
        val size = drawScope.size
        
        drawBackground(drawScope, size)
        drawGround(drawScope, size)
        drawCoins(drawScope, state.coins, frame)
        drawObstacles(drawScope, state.obstacles)
        drawPlayer(drawScope, state.player, frame)
        drawHUD(drawScope, size, state)
    }
 
    private fun drawBackground(drawScope: DrawScope, size: Size) {
        drawScope.drawRect(
            brush = Brush.verticalGradient(
                colors = listOf(Color(0xFF0D1B4A), Color(0xFF1A237E))
            ),
            size = size
        )
    }

    private fun drawGround(drawScope: DrawScope, size: Size) {
        val floorY = Player.FLOOR_Y + 20f
        drawScope.drawRect(
            color = Color(0xFF388E3C),
            topLeft = Offset(0f, floorY),
            size = Size(size.width, size.height - floorY)
        )
    }

    private fun drawCoins(drawScope: DrawScope, coins: List<Coin>, frame: Long) {
        coins.filter { !it.collected }.forEach { coin ->
            val bounce = sin(frame * 0.1f + coin.phase) * 5f
            drawScope.drawCircle(
                color = Color(0xFFFFD600),
                radius = 8f,
                center = Offset(coin.x, coin.y + bounce)
            )
        }
    }

    private fun drawObstacles(drawScope: DrawScope, obstacles: List<Obstacle>) {
        val floorY = Player.FLOOR_Y + 20f
        obstacles.forEach { o ->
            drawScope.drawRect(
                color = Color(0xFFD32F2F),
                topLeft = Offset(o.x, floorY - o.height),
                size = Size(o.width.toFloat(), o.height.toFloat())
            )
        }
    }
 
    private fun drawPlayer(drawScope: DrawScope, player: Player, frame: Long) {
        // Parpadeo de invencibilidad
        val alpha = if (player.isInvincible && (frame / 4) % 2 == 0L) 0.3f else 1f
        val yPos = player.y
 
        // Cuerpo del personaje
        drawScope.drawRect(
            color = Color(0xFFE65100).copy(alpha = alpha),
            topLeft = Offset(player.x - 6f, yPos - 10f),
            size = Size(20f, 24f)
        )
 
        // Casco UTNG
        drawScope.drawRect(
            color = Color(0xFF1A237E).copy(alpha = alpha),
            topLeft = Offset(player.x - 5f, yPos - 24f),
            size = Size(18f, 14f)
        )
    }
 
    private fun drawHUD(drawScope: DrawScope, size: Size, state: GameState) {
        // Puntuación inferior
        repeat(state.lives) { i ->
             drawScope.drawCircle(
                 color = Color.Red,
                 radius = 6f,
                 center = Offset(20f + i * 20f, 40f)
             )
        }
    }
}

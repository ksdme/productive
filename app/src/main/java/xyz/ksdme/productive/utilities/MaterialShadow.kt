package xyz.ksdme.productive.utilities

import android.content.Context
import android.graphics.Color
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.graphics.drawable.LayerDrawable
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.RoundRectShape
import android.support.v4.view.ViewCompat
import android.util.AttributeSet
import android.util.Log
import android.view.Gravity
import android.view.View
import xyz.ksdme.productive.R

class MaterialShadow {
    companion object {
        private val tag = "materialShadow"

        fun generate(target: View,
                     backgroundColor: Int,
                     shadowColor: Int,
                     cornerRadius: Float,
                     elevation: Int,
                     shadowGravity: Int,
                     horizontal: Boolean = true,
                     vertical: Boolean = true
        ): Drawable {

            val spacingShape = Rect()

            if (horizontal) {
                spacingShape.left = elevation
                spacingShape.right = elevation
            }

            val dy = when(shadowGravity) {
                Gravity.CENTER -> {
                    if (vertical) {
                        spacingShape.top = elevation
                        spacingShape.bottom = elevation
                    }

                    0.toFloat()
                }

                Gravity.TOP -> {
                    if (vertical) {
                        spacingShape.top = elevation * 2
                        spacingShape.bottom = elevation
                    }

                    (-1 * elevation / 2).toFloat()
                }

                Gravity.BOTTOM -> {
                    if (vertical) {
                        spacingShape.top = elevation
                        spacingShape.bottom = elevation * 2
                    }

                    (elevation / 2).toFloat()
                }

                else -> {
                    spacingShape.top = 0
                    spacingShape.bottom = 0

                    0.toFloat()
                }
            }

            val shapeDrawable = ShapeDrawable()
            shapeDrawable.setPadding(spacingShape)

            shapeDrawable.paint.color = backgroundColor
            shapeDrawable.paint.setShadowLayer(cornerRadius / 3, 0F, dy, shadowColor)

            target.setLayerType(ViewCompat.LAYER_TYPE_SOFTWARE, shapeDrawable.paint)

            val rounded = floatArrayOf(cornerRadius, cornerRadius, cornerRadius, cornerRadius,
                                       cornerRadius, cornerRadius, cornerRadius, cornerRadius)
            shapeDrawable.shape = RoundRectShape(rounded, null, null)

            val drawable = LayerDrawable(arrayOf(shapeDrawable))
            drawable.setLayerInset(0,
                                   if (horizontal) elevation else 0,
                                   if (vertical) (elevation * 2) else  0,
                                   if (horizontal) elevation else 0,
                                   if (vertical) (elevation * 2) else 0)

            return drawable
        }

        fun generate(view: View, context: Context, attrs: AttributeSet?): Drawable {
            val typed = context.theme.obtainStyledAttributes(
                attrs, R.styleable.material_shadow, 0, 0)

            val backgroundColor = typed.getColor(
                R.styleable.material_shadow_backgroundColor, Color.WHITE)
            val shadowColor = typed.getColor(
                R.styleable.material_shadow_shadowColor, Color.DKGRAY)
            val elevation = typed.getDimension(
                R.styleable.material_shadow_elevation, 2F).toInt()
            val cornerRadius = typed.getDimension(
                R.styleable.material_shadow_cornerRadius, 0F)
            val shadowGravity = typed.getInt(
                R.styleable.material_shadow_shadowGravity, Gravity.BOTTOM)
            val horizontal = !typed.getBoolean(
                R.styleable.material_shadow_verticalOnly, false)
            val vertical = !typed.getBoolean(
                R.styleable.material_shadow_horizontalOnly, false)

            // Good Citizen
            typed.recycle()

            return MaterialShadow.generate(
                view, backgroundColor, shadowColor, cornerRadius,
                elevation, shadowGravity, horizontal, vertical)
        }

    }
}

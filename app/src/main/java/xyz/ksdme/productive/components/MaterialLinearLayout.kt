package xyz.ksdme.productive.components

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import xyz.ksdme.productive.utilities.MaterialShadow

class MaterialLinearLayout : LinearLayout {

    constructor(context: Context): super(context) {
        this.materialize(context)
    }

    constructor(context: Context, attrs: AttributeSet?): super(context, attrs) {
        this.materialize(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int):
            super(context, attrs, defStyleAttr) {
        this.materialize(context, attrs)
    }

    private fun materialize(context: Context, attrs: AttributeSet? = null) {
        this.background = MaterialShadow.generate(this, context, attrs)
    }

}

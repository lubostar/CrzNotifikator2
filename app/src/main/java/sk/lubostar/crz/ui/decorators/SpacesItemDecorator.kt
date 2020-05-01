package sk.lubostar.crz.ui.decorators

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class SpacesItemDecorator(private val space: Int): RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView,
                                state: RecyclerView.State){
        outRect.let {
            it.left = space
            it.right = space
            it.bottom = space
            it.top = if(parent.getChildLayoutPosition(view) == 0) space else 0
        }
    }
}
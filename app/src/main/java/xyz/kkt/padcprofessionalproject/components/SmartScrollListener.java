package xyz.kkt.padcprofessionalproject.components;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Created by Lenovo on 11/26/2017.
 */

public class SmartScrollListener extends RecyclerView.OnScrollListener {

    public interface OnSmartScrollListener {
        void onListEndReach();
    }

    private int visibleItemCount, pastVisibleItems, totalItemCount;
    private boolean isListEndReached = false;
    private int previousDy, currentDy;

    private OnSmartScrollListener mSmartScrollListener;

    private boolean mAlreadyShown = false;

    public SmartScrollListener(OnSmartScrollListener smartScrollListener) {
        this.mSmartScrollListener = smartScrollListener;
    }

    @Override
    public void onScrolled(RecyclerView rv, int dx, int dy) {
        super.onScrolled(rv, dx, dy);

        currentDy = dy;
        if (currentDy > previousDy) {
            //from top to bottom
        } else if (currentDy < previousDy) {
            //from bottom to top
            isListEndReached = false;
            //mAlreadyShown = false;
        }

        visibleItemCount = rv.getLayoutManager().getChildCount();
        totalItemCount = rv.getLayoutManager().getItemCount();
        pastVisibleItems = ((LinearLayoutManager) rv.getLayoutManager()).findFirstVisibleItemPosition();

        if ((visibleItemCount + pastVisibleItems) < totalItemCount) {
            mAlreadyShown = false;
        }

        previousDy = currentDy;
    }

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int scrollState) {
        super.onScrollStateChanged(recyclerView, scrollState);
        if (scrollState == RecyclerView.SCROLL_STATE_IDLE) {
            if ((visibleItemCount + pastVisibleItems) >= totalItemCount
                    && !isListEndReached && !mAlreadyShown) {

                isListEndReached = true;
                mSmartScrollListener.onListEndReach();
                mAlreadyShown = true;

            }
        }
    }
}
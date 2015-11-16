package com.wan7451.radiamenu;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.PopupWindow;
/**
 * Created by wanggang on 15/11/15.
 */

public class RadialMenuHelper {

    private RotateAnimation rotate;
    private ScaleAnimation scale;
    private TranslateAnimation move;
    private long animationSpeed = 0;

    /**
     * This function initialises the popup window.
     * @param context - Application context
     * @return An PopupWindow Object
     */
    protected PopupWindow initPopup(Context context) {
        PopupWindow window = new PopupWindow(context);
        window.setWidth(WindowManager.LayoutParams.WRAP_CONTENT);
        window.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        window.setTouchable(true);
        window.setFocusable(true);
        window.setOutsideTouchable(true);
        window.setBackgroundDrawable(new BitmapDrawable());
        return window;
    }

    /**
     * This function initialises and sets the open animation for the radial menu widget.
     * @param view - View to be animated.
     * @param xPosition - Current view X position.
     * @param yPosition - Current view Y position.
     * @param xSource - Destination X position.
     * @param ySource - Destination Y position
     */
    protected void onOpenAnimation(View view, int xPosition, int yPosition,
                                   int xSource, int ySource) {
        rotate = new RotateAnimation(0, 360, xPosition, yPosition);
        scale = new ScaleAnimation(0, 1, 0, 1, xPosition, yPosition);
        scale.setInterpolator(new DecelerateInterpolator());
        move = new TranslateAnimation(xSource - xPosition, 0, ySource
                - yPosition, 0);
        // Setup the animation sequence
        AnimationSet spriteAnimation = new AnimationSet(true);
        spriteAnimation.addAnimation(rotate);
        spriteAnimation.addAnimation(scale);
        spriteAnimation.addAnimation(move);
        spriteAnimation.setDuration(animationSpeed);
        // Start the animation
        view.startAnimation(spriteAnimation);
    }

    /**
     * This function initialises and sets the open animation for the radial menu widget along with the animation time.
     * @param view - View to be animated.
     * @param xPosition - Current view X position.
     * @param yPosition - Current view Y position.
     * @param xSource - Destination X position.
     * @param ySource - Destination Y position
     * @param animTime - View animation time.
     */
    protected void onOpenAnimation(View view, int xPosition, int yPosition,
                                   int xSource, int ySource, long animTime) {
        rotate = new RotateAnimation(0, 360, xPosition, yPosition);
        scale = new ScaleAnimation(0, 1, 0, 1, xPosition, yPosition);
        scale.setInterpolator(new DecelerateInterpolator());
        move = new TranslateAnimation(xSource - xPosition, 0, ySource
                - yPosition, 0);
        // Setup the animation sequence
        AnimationSet spriteAnimation = new AnimationSet(true);
        spriteAnimation.addAnimation(rotate);
        spriteAnimation.addAnimation(scale);
        spriteAnimation.addAnimation(move);
        spriteAnimation.setDuration(animTime);
        // Start the animation
        view.startAnimation(spriteAnimation);
    }

    /**
     * This function initialises and sets the closing animation for the radial menu widget.
     * @param view - View to be animated.
     * @param xPosition - Current view X position.
     * @param yPosition - Current view Y position.
     * @param xSource - Destination X position.
     * @param ySource - Destination Y position
     */
    protected void onCloseAnimation(View view, int xPosition, int yPosition,
                                    int xSource, int ySource) {
        rotate = new RotateAnimation(360, 0, xPosition, yPosition);
        scale = new ScaleAnimation(1, 0, 1, 0, xPosition, yPosition);
        scale.setInterpolator(new AccelerateInterpolator());
        move = new TranslateAnimation(0, xSource - xPosition, 0, ySource
                - yPosition);
        // Setup the animation sequence
        AnimationSet spriteAnimation = new AnimationSet(true);
        spriteAnimation.addAnimation(rotate);
        spriteAnimation.addAnimation(scale);
        spriteAnimation.addAnimation(move);
        spriteAnimation.setDuration(animationSpeed);
        // Start the animation
        view.startAnimation(spriteAnimation);
    }

    /**
     * This function initialises and sets the closing animation for the radial menu widget along with the animation time.
     * @param view - View to be animated.
     * @param xPosition - Current view X position.
     * @param yPosition - Current view Y position.
     * @param xSource - Destination X position.
     * @param ySource - Destination Y position
     * @param animTime - View animation time.
     */
    protected void onCloseAnimation(View view, int xPosition, int yPosition,
                                    int xSource, int ySource, long animTime) {
        rotate = new RotateAnimation(360, 0, xPosition, yPosition);
        scale = new ScaleAnimation(1, 0, 1, 0, xPosition, yPosition);
        scale.setInterpolator(new AccelerateInterpolator());
        move = new TranslateAnimation(0, xSource - xPosition, 0, ySource
                - yPosition);
        // Setup the animation sequence
        AnimationSet spriteAnimation = new AnimationSet(true);
        spriteAnimation.addAnimation(rotate);
        spriteAnimation.addAnimation(scale);
        spriteAnimation.addAnimation(move);
        spriteAnimation.setDuration(animTime);
        // Start the animation
        view.startAnimation(spriteAnimation);
    }

    /**
     *
     * @param px
     * @param py
     * @param xRadiusCenter
     * @param yRadiusCenter
     * @param innerRadius
     * @param outerRadius
     * @param startAngle
     * @param sweepAngle
     * @return
     */
    protected boolean pntInWedge(double px, double py, float xRadiusCenter,
                                 float yRadiusCenter, int innerRadius, int outerRadius,
                                 double startAngle, double sweepAngle) {
        double diffX = px - xRadiusCenter;
        double diffY = py - yRadiusCenter;
        double angle = Math.atan2(diffY, diffX);
        if (angle < 0)
            angle += (2 * Math.PI);
        if (startAngle >= (2 * Math.PI)) {
            startAngle = startAngle - (2 * Math.PI);
        }
        // checks if point falls between the start and end of the wedge
        if ((angle >= startAngle && angle <= startAngle + sweepAngle)
                || (angle + (2 * Math.PI) >= startAngle && (angle + (2 * Math.PI)) <= startAngle
                + sweepAngle)) {
            // checks if point falls inside the radius of the wedge
            double dist = diffX * diffX + diffY * diffY;
            if (dist < outerRadius * outerRadius
                    && dist > innerRadius * innerRadius) {
                return true;
            }
        }
        return false;
    }

    /**
     *
     * @param px
     * @param py
     * @param x1
     * @param y1
     * @param radius
     * @return
     */
    protected boolean pntInCircle(double px, double py, double x1, double y1,
                                  double radius) {
        double diffX = x1 - px;
        double diffY = y1 - py;
        double dist = diffX * diffX + diffY * diffY;
        return dist < radius * radius;
    }

}

package com.wan7451.advancedview;

import android.graphics.Path;
import android.graphics.RectF;
/**
 * Created by wanggang on 15/11/15.
 */
    public class RadialMenuWedge extends Path {
        private int x, y;
        private int InnerSize, OuterSize;
        private float StartArc;
        private float ArcWidth;

        /**
         *
         * @param x
         * @param y
         * @param InnerSize
         * @param OuterSize
         * @param StartArc
         * @param ArcWidth
         */
        protected RadialMenuWedge(int x, int y, int InnerSize, int OuterSize,
                                  float StartArc, float ArcWidth) {
            super();
            if (StartArc >= 360) {
                StartArc = StartArc - 360;
            }
            this.x = x;
            this.y = y;
            this.InnerSize = InnerSize;
            this.OuterSize = OuterSize;
            this.StartArc = StartArc;
            this.ArcWidth = ArcWidth;
            this.buildPath();
        }

        /**
         *
         */
        protected void buildPath() {
            final RectF rect = new RectF();
            final RectF rect2 = new RectF();
            // Rectangles values
            rect.set(this.x - this.InnerSize, this.y - this.InnerSize, this.x
                    + this.InnerSize, this.y + this.InnerSize);
            rect2.set(this.x - this.OuterSize, this.y - this.OuterSize, this.x
                    + this.OuterSize, this.y + this.OuterSize);
            this.reset();
            // this.moveTo(100, 100);
            this.arcTo(rect2, StartArc, ArcWidth);
            this.arcTo(rect, StartArc + ArcWidth, -ArcWidth);
            this.close();
        }
    }

package flax.ie.client.animations;

import com.google.gwt.animation.client.Animation;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Style.Unit;

/**
 * Make a singleton at some stage #lazy
 * 
 * Robbed most of this code from http://www.giantflyingsaucer.com/blog/?p=1548 so thanks for that :p
 * @author ciaran
 *
 */
public class slideInOut extends Animation {

    private final Element element;
    private int startX;
    private int startY;
    private int finalX;
    private int finalY;
 
    public slideInOut(Element element)
    {
        this.element = element;
    }
 
    public void scrollTo(int x, int y, int milliseconds)
    {
        this.finalX = x;
        this.finalY = y;
 
        startX = element.getOffsetLeft();
        startY = element.getOffsetTop();
 
        run(milliseconds);
    }
 
    @Override
    protected void onUpdate(double progress)
    {
        double positionX = startX + (progress * (this.finalX - startX));
        double positionY = startY + (progress * (this.finalY - startY));
 
        this.element.getStyle().setLeft(positionX, com.google.gwt.dom.client.Style.Unit.PX);
        this.element.getStyle().setTop(positionY, com.google.gwt.dom.client.Style.Unit.PX);
    }
 
    @Override
    protected void onComplete()
    {
        super.onComplete();
        this.element.getStyle().setLeft(this.finalX, com.google.gwt.dom.client.Style.Unit.PX);
        this.element.getStyle().setTop(this.finalY, com.google.gwt.dom.client.Style.Unit.PX);
    }
}
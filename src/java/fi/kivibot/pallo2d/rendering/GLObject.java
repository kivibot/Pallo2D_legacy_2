package fi.kivibot.pallo2d.rendering;

/**
 *
 * @author Nicklas Ahlskog
 */
public class GLObject {

    private final boolean dynamic;
    private int id;

    public GLObject(int id) {
        this.id = id;
        this.dynamic = false;
        System.out.println("dyn false");
    }

    public GLObject() {
        this.id = -1;
        this.dynamic = true;
        System.out.println("dyn true");
    }

    public boolean isDynamic() {
        return dynamic;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if(!isDynamic()){
            throw new RuntimeException("Trying to change the id of a static object!");
        }
        this.id = id;
    }

}

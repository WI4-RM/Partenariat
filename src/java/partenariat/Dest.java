/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package partenariat;

/**
 *
 * @author charles
 */
public class Dest {
    private float x;
    private float y;
    private int z;
    private String name;
    private String city;
    private int id;

    public void setCity(String city) {
        this.city = city;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }


    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void setZ(int z) {
        this.z = z;
    }
    
    
    public String getCity() {
        return city;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }



    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public int getZ() {
        return z;
    }

}

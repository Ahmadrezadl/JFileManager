package Logic;

public class Memento {
    String[] addresses;
    int pointer;
    public Memento()
    {
        addresses = new String[10];
        pointer = 0;
    }
    public String goLeft(String now){
        addresses[pointer] = now;
        if(addresses[pointer - 1] != null)
            pointer--;
        debug();

        return addresses[pointer];
    }
    public String goRight(){
        if(addresses[pointer + 1] != null)
            pointer++;
        debug();

        return addresses[pointer];
    }
    public void addRight(String link)
    {
        if(pointer>8)
        {
            System.arraycopy(addresses , 1 , addresses , 0 , 9);
            pointer--;
        }
        addresses[pointer] = link;
        System.out.println(addresses);
        pointer++;
        for(int i = pointer;i < 10;i++)
            addresses[i] = null;
        debug();
    }
    private void debug()
    {
        System.out.println("Wait");
        for(String s : addresses)
            System.out.println(s);
        System.out.println("" + pointer + "_______");
    }
}

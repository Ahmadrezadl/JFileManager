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
        System.out.println(addresses);        debug();

        return addresses[pointer];
    }
    public String goRight(){
        if(addresses[pointer + 1] != null)
            pointer++;
        System.out.println(addresses);
        debug();

        return addresses[pointer];
    }
    public void addRight(String link)
    {
        if(pointer>8)
        {
            for(int i = 1;i <= 9;i++)
            {
                addresses[i-1] = addresses[i];
            }
            pointer--;
        }
        addresses[pointer] = link;
        System.out.println(addresses);
        pointer++;
        debug();
    }
    private void debug()
    {
        for(String s : addresses) System.out.println(s);
        System.out.println("" + pointer + "_______");
    }
}

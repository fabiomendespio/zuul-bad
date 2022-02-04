import java.util.ArrayList;
/**
 * Escreva uma descrição da classe Player aqui.
 * 
 * @author (seu nome) 
 * @version (um número da versão ou uma data)
 */
public class Player
{
    
    private String name;
    private int maxWeightCarry = 5;
    private ArrayList <Item> bolsa = new ArrayList<>();
    private Room currentRoom;
    private static int pesoParcial = 0;

    public Player(String name)
    {
        this.name = name;
    }

    public void dropItem(String soltaItem)
    {
        int i;
        for(i = 0; i< bolsa.size(); i++) {
            if (bolsa.get(i).getItemName().equals(soltaItem)) {
                pesoParcial = pesoParcial - bolsa.get(i).getItemWeight();
                System.out.println("Você soltou " + bolsa.get(i).getItemName());
                currentRoom.getItemList().add(bolsa.get(i));
                bolsa.remove(i);
            }
        }
    }

    public void pickUpItem(String pegaItem)
    {
        int i;
        for(i = 0; i< currentRoom.getItemList().size(); i++) 
        {
            if (currentRoom.getItem(i).getItemName().equals(pegaItem)) 
            {
                pesoParcial = pesoParcial + currentRoom.getItem(i).getItemWeight();
                
                if(pesoParcial > getMaxWeightCarry())
                {
                      System.out.println("se pegar este item você vai ultrapssar o peso maximo da sua bolsa, solte algum item");
                }else
                    {
                        bolsa.add(currentRoom.getItem(i));
                        System.out.println("Você pegou " + currentRoom.getItem(i).getItemName());
                        currentRoom.removeItemList(i);
                        
                    }
            }   
        }
    }

    public int checkWeightBag(){
        int pesoTotal = 0;
        for(int i =0; i < bolsa.size(); i++){
            pesoTotal = pesoTotal + bolsa.get(i).getItemWeight();
        }

        return pesoTotal;
    }

    public Room getCurrentRoom(){
        return currentRoom;
    }
    
    public void setCurrentRoom(Room currentRoom){
        this.currentRoom = currentRoom;
    }
    
    public String getCurrentRoomDescription(){
        return currentRoom.getLongDescription();
    }

    public int getMaxWeightCarry() {
        return maxWeightCarry;
    }
    
    public ArrayList<Item> getItemBag()
    {
        return bolsa;
    }

    public String getBag()
    {
        String result = "";
        if (bolsa.isEmpty()) {
            return "sem itens aqui";
        }
        for (Item itemBag : bolsa) {
            result += "[" + itemBag.getItemName() + "] ";
        }
          
        return result += "O peso da bolsa é: " + checkWeightBag();
    }

}

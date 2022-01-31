/**
 *  This class is the main class of the "World of Zuul" application.
 *  "World of Zuul" is a very simple, text based adventure game.  Users
 *  can walk around some scenery. That's all. It should really be extended
 *  to make it more interesting!
 *
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 *
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 *
 * @author  Michael Kölling and David J. Barnes
 * @version 2016.02.29
 */

import java. util. *;

public class Game
{
    private Parser parser;
    private Player player;
    private Stack<Room> backStack = new Stack<Room>();


    /**
     * Create the game and initialise its internal map.
     */
    public Game()
    {
        createRooms();
        parser = new Parser();
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        Room entrada, jardim, hall, salao_principal, sala_de_estar, sala_de_jantar, cozinha, salao_de_festas, sala_de_visitas, salao_de_jogos, biblioteca, escritorio, sala_secreta;

        Item castical, chave_inglesa;

        player = new Player ("teste");


        // create the rooms
        entrada = new Room("na entrada");
        jardim = new Room("no jardim");
        hall = new Room("no hall");
        salao_principal = new Room("no salão principal");
        sala_de_estar = new Room("na sala de estar");
        sala_de_jantar = new Room("na sala de jantar");
        cozinha = new Room("na cozinha");
        salao_de_festas = new Room("no salão de festas");
        sala_de_visitas = new Room("na sala de visitas");
        salao_de_jogos = new Room("na salão de jogos");
        biblioteca = new Room("na biblioteca");
        escritorio = new Room("no escritório");
        sala_secreta = new Room("na sala secreta");

        castical = new Item("castical", "um artefato antigo", 2);
        chave_inglesa = new Item("chave_inglesa", "uma ferramenta suja", 5);

        entrada.setExit("leste", jardim);
        entrada.setExit("sul", hall);
        entrada.setExit("nordeste", sala_secreta);


        jardim.setExit("oeste", entrada);

        hall.setExit("norte", entrada);
        hall.setExit("leste", sala_de_estar);
        hall.setExit("sul", salao_principal);
        hall.setExit("oeste", sala_de_visitas);

        sala_de_estar.setExit("oeste", hall);

        sala_de_visitas.setExit("leste", hall);

        salao_principal.setExit("norte", hall);
        salao_principal.setExit("sul", sala_de_jantar);
        salao_principal.setExit("oeste", biblioteca);

        biblioteca.setExit("oeste", escritorio);
        biblioteca.setExit("leste", salao_principal);

        escritorio.setExit("leste", biblioteca);
        escritorio.setExit("baixo", sala_secreta);

        sala_de_jantar.setExit("norte", salao_principal);
        sala_de_jantar.setExit("leste", cozinha);
        sala_de_jantar.setExit("sul", salao_de_festas);

        cozinha.setExit("oeste", sala_de_jantar);

        salao_de_festas.setExit("norte", sala_de_jantar);
        salao_de_festas.setExit("oeste", salao_de_jogos);

        salao_de_jogos.setExit("leste", salao_de_festas);

        entrada.addItens(castical);
        entrada.addItens(chave_inglesa);
        sala_secreta.addItens(chave_inglesa);

        player.setCurrentRoom(entrada); // start game outside
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play()
    {
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.

        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Obrigado por jogar. Até mais!");
    }

    public static void main(String[] args){
        Game g = new Game();
        g.play();
    }
    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {

        System.out.println();
        System.out.println("Bem vindo ao jogo de texto de investigação!");
        System.out.println("Este jogo é desenvolvido por Fabio e Camila.");
        System.out.println("Você é um detetive que investiga um assassinato em uma mansão!");
        System.out.println("Procure pistas pela mansão, interrogue os suspeitos e deduza o mistério.");
        System.out.println("Você precisa descobrir quem comenteu o assassinato, com qual arma e o local do crime.");
        System.out.println("Escreva 'ajuda' se você precisar de ajuda.");
        System.out.println();


        System.out.println(player.getCurrentRoomDescription());
    }


    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command)
    {
        boolean wantToQuit = false;

        if(command.isUnknown()) {
            System.out.println("O comando parece errado...digite ajuda se precisar");
            return false;
        }

        String commandWord = command.getCommandWord();
        if (commandWord.equals("ajuda")) {
            printHelp();
        }
        else if (commandWord.equals("para")) {
            goRoom(command);
        }
        else if (commandWord.equals("sair")) {
            wantToQuit = quit(command);
        }
        else if (commandWord.equals("investigar")) {
            investigar(command);
        }
        else if (commandWord.equals("falar")) {
            falar(command);
        }
        else if (commandWord.equals("voltar")) {
            voltar(command);
        }
        else if (commandWord.equals("pegar")) {
            pegar(command);
        }
        else if (commandWord.equals("soltar")) {
            soltar(command);
        }
        else if (commandWord.equals("bolsa")) {
            showBag(command);
        }
        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the
     * command words.
     */
    private void printHelp()
    {
        System.out.println("Continue investigando, a solução é elementar!");
        System.out.println();
        System.out.println(parser.showCommands());
    }

    private void pegar(Command command)
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("pegar qual item? Digite o nome do item");
            return;
        }
        if(player.getCurrentRoom().getItemList().isEmpty() ){
            System.out.println("não tem itens por aqui");
        }
        String pegaItem = command.getSecondWord();
        player.pickUpItem(pegaItem);

    }

    private void soltar(Command command)
    {
        if(command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("apenas digite soltar");
            return;
        }
        player.dropItem();
    }

    private void falar(Command command)
    {
        if(command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("apenas digite falar");
            return;
        }
        System.out.println("Falar com o npc");
    }

    private void investigar(Command command)
    {
        if(command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("apenas digite investigar");
            return;
        }
        System.out.println(" descricao: " + player.getCurrentRoom().getListDescriptionItens());
    }

    private void voltar(Command command)
    {
        if(command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("apenas digite voltar");
            return;
        }
        if(backStack.empty()){
            // if there is no second word, we don't know where to go...
            System.out.println("Não tem pra onde voltar!");
            return;
        }
        else{
            int max = 0;
            for(int i= 0; i < backStack.size(); i++){
                max = i;
            }
            Room backRoom = backStack.get(max);
            player.setCurrentRoom(backRoom);
            backStack.pop();
            System.out.println(player.getCurrentRoomDescription());
        }
    }
    
      private void showBag(Command command){
        System.out.println(player.getBag());
    }

    /**
     * Try to go in one direction. If there is an exit, enter
     * the new room, otherwise print an error message.
     */
    private void goRoom(Command command)
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Ir para onde? Digite para e depois uma direçao como norte, leste, sul, oeste, cima ou baixo");
            return;
        }

        String direction = command.getSecondWord();
        backStack.push(player.getCurrentRoom());

        // Try to leave current room.
        Room nextRoom = player.getCurrentRoom().getExit(direction);

        if (nextRoom == null) {
            System.out.println("Não tem passagem por essa direção!");
        }
        else {
            player.setCurrentRoom(nextRoom);
            System.out.println(player.getCurrentRoomDescription());
        }
    }

    /**
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command)
    {
        if(command.hasSecondWord()) {
            System.out.println("Digite apenas sair se quiser finalizar o jogo.");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }
}

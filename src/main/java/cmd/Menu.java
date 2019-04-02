package cmd;

import api.*;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;

public class Menu {

    private List<Game> PhyGames = new ArrayList<>();
    private List<Game> DigiGames = new ArrayList<>();
    private List<Game> allGame = new ArrayList<>();
    private List<Game> filteredGames = new ArrayList<>();
    private List<Game> favorites = new ArrayList<>();
    private String[] platforms = {"PS4game", "XboxOneGame", "NintendoGame", "PCGame", "Crossplatform"};
    XMLParser xmlParser = new XMLParser("/home/beszteri/Dokumentumok/Codecool/java/ModellingReworked/src/main/resources/Games.xml");
    XMLwriter xmLwriter = new XMLwriter();
    private String searcedSubPlat;

    public void gameLoader () {
        PhyGames.clear();
        DigiGames.clear();
        for(String string : platforms){
            NodeList nodeList = xmlParser.getDocument().getElementsByTagName(string);
            for (int i = 0; i < nodeList.getLength(); i++) {
                List<String> attributes = new ArrayList<>();

                attributes.add(nodeList.item(i).getAttributes().getNamedItem("name").getNodeValue());
                attributes.add(nodeList.item(i).getAttributes().getNamedItem("developer").getNodeValue());
                attributes.add(nodeList.item(i).getAttributes().getNamedItem("publisher").getNodeValue());
                attributes.add(nodeList.item(i).getAttributes().getNamedItem("metacritic").getNodeValue());
                attributes.add(nodeList.item(i).getAttributes().getNamedItem("genre").getNodeValue());
                attributes.add(nodeList.item(i).getAttributes().getNamedItem("accessibility").getNodeValue());
                attributes.add(nodeList.item(i).getNodeName());
                if (attributes.get(5).equals("Disk")) {
                    PhyGames.add(new PhysicalGame(attributes));
                }else{
                    DigiGames.add(new DigitalGame(attributes));
                }
            }
        }
        allGame.addAll(DigiGames);
        allGame.addAll(PhyGames);
    }

    public List<Game> getPhyGames() {
        return PhyGames;
    }

    public List<Game> getDigiGames() {
        return DigiGames;
    }

    public List<Game> getFilteredGames() {
        return filteredGames;
    }


    public void makeOneList(){
        gameLoader();
        allGame.addAll(DigiGames);
        allGame.addAll(PhyGames);
    }


    public void createFavList(String name){
        PhyGames.clear();
        DigiGames.clear();
        filteredGames.clear();
        gameLoader();
        makeOneList();
        for (Game g : allGame){
            if (g.getName().equals(name)){
                if (favorites.contains(g)){
                    System.out.println("Already added to the favorites");
                }else {
                    favorites.add(g);
                }
            }
        }
    }

    public void deleteGame(String game){
        PhyGames.clear();
        DigiGames.clear();
        filteredGames.clear();
        makeOneList();
        for (int g = 0; g < allGame.size(); g++){  //ConcurrentModificationException jön elő ench for loop-nál. wtf?
            if (allGame.get(g).getName().equals(game)){
                allGame.remove(g);
            }
        }
    }

    public void showAllGame(){

        for (Game g : allGame){
            System.out.println("-----------------------------------------------------------------------" +
                    "-------------------------------------------------------------------------------" +
                    "-------------------------------------------------------------------------");
            System.out.println(g);
        }
    }

    public void addNewGame(List<String> attributes){
        if (attributes.get(5).equals("Disk")){
            allGame.add(new PhysicalGame(attributes));
        }else {
            allGame.add(new DigitalGame(attributes));
        }
    }

    public List<Game> getAllGame() {
        return allGame;
    }

    public List<Game> getFavorites() {
        return favorites;
    }

    /*public void deleteGame(String game){
        PhyGames.clear();
        DigiGames.clear();
        filteredGames.clear();
        makeOneList();
        for (int g = 0; g < allGame.size(); g++){
            if (allGame.get(g).getName().equals(game)){
                allGame.remove(g);
            }
        }
        String searchedMainPlat;
        searcedSubPlat = allGame.get(0).getSubPlatform();
        if (searcedSubPlat.equals("PS4game")){
            searchedMainPlat = "PS4games";
        }else if (searcedSubPlat.equals("XboxOneGame")){
            searchedMainPlat = "XboxOneGames";
        }else if (searcedSubPlat.equals("NintendoGame")){
            searchedMainPlat = "NintendoGames";
        }else if (searcedSubPlat.equals("PCGame")){
            searchedMainPlat = "PCGames";
        }else {
            searchedMainPlat = "Crossplatforms";
        }
        xmLwriter.WriteNewXmlFile(allGame.get(0).getName(), allGame.get(0).getDeveloper(),allGame.get(0).getPublisher(),allGame.get(0).getMetacritic(),allGame.get(0).getGenre(),allGame.get(0).getAccessibility(), searchedMainPlat, searcedSubPlat, "/home/beszteri/Dokumentumok/Codecool/java/ModellingReworked/src/main/resources/Games.xml");
        System.out.println(allGame.get(0));
        XMLParser xmlParser = new XMLParser("/home/beszteri/Dokumentumok/Codecool/java/ModellingReworked/src/main/resources/Games.xml");

        for (int g = 1; g < allGame.size(); g++) {
           // searcedSubPlat.equals(allGame.get(g).getSubPlatform());
           // searchedMainPlat = searcedSubPlat + "s";
            if(allGame.get(g).getSubPlatform().equals(searcedSubPlat)){
                xmLwriter.XmlAppender2(xmlParser.getDocument(),allGame.get(g).getSubPlatform(),allGame.get(g).getName(),allGame.get(g).getDeveloper(),allGame.get(g).getPublisher(),allGame.get(0).getMetacritic(),allGame.get(g).getGenre(),allGame.get(g).getAccessibility());
                System.out.println(g);
            }else {
                searcedSubPlat = allGame.get(g).getSubPlatform();
                searchedMainPlat = searcedSubPlat + "s";
                xmLwriter.XmlAppender(xmlParser.getDocument(), searchedMainPlat, searcedSubPlat, allGame.get(g).getName(), allGame.get(g).getDeveloper(), allGame.get(g).getPublisher(), allGame.get(g).getMetacritic(), allGame.get(g).getGenre(), allGame.get(g).getAccessibility());
                System.out.println(g);
            }
        }
    }

    public void showAllGame() {
        for (Game g : allGame) {

        }
    }*/

}

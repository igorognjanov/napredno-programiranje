package labs.Collections.ChatSystemAdministration;
import com.sun.source.tree.Tree;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.TreeSet;
import java.util.function.Supplier;

class ChatRoom{
    private String name;
    private TreeSet<String> users;

    public String getName() {
        return name;
    }

    public ChatRoom(String name){
        this.name = name;
        users = new TreeSet<> ();
    }

    public void addUser(String username){
        users.add (username);
    }

    public void removeUser(String username){
        users.remove (username);
    }

    public boolean hasUser(String username){
        return users.contains (username);
    }

    public int numUsers(){
        return users.size ();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass () != o.getClass ()) return false;
        ChatRoom chatRoom = (ChatRoom) o;
        return name.equals (chatRoom.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash (name);
    }

    public String toString(){
        if(users.isEmpty ())
            return String.format ("%s\nEMPTY\n", name);
        String ret = name;
        for(String user : users){
            ret += "\n" + user;
        }
        return ret + "\n";
    }
}

class ChatSystem{
    private Map<String, HashSet<String>> users;
    private TreeMap<String, ChatRoom> rooms;
    public ChatSystem(){
        users = new HashMap<> ();
        rooms = new TreeMap<String, ChatRoom>();
    }

    public void addRoom(String roomName){
        rooms.put (roomName, new ChatRoom (roomName));
    }

    public void removeRoom(String roomName){
        rooms.remove (roomName);
        for(HashSet<String> set : users.values ()){
            set.remove (roomName);
        }
    }

    public ChatRoom getRoom(String roomName) throws NoSuchRoomException{
        if(!rooms.containsKey (roomName)) throw new NoSuchRoomException (roomName);
        return rooms.get (roomName);
    }

    public void register(String username)throws NoSuchElementException{
        users.putIfAbsent (username, new HashSet<> ());
        ChatRoom cr = rooms.values ().stream ()
                .min (Comparator.comparing (ChatRoom::numUsers).thenComparing (ChatRoom::getName))
                .orElse (null);
        if(cr != null) {
            cr.addUser (username);
            users.get (username).add (cr.getName ());
        }
    }

    public void registerAndJoin(String userName, String roomName){
        users.putIfAbsent (userName, new HashSet<> ());
        users.get (userName).add (roomName);
        rooms.get (roomName).addUser (userName);
    }

    public void joinRoom(String userName, String roomName) throws NoSuchUserException, NoSuchRoomException {
        if(!users.containsKey (userName)) throw new NoSuchUserException (userName);
        if(!rooms.containsKey (roomName)) throw new NoSuchRoomException (roomName);
        rooms.get (roomName).addUser (userName);
        users.get (userName).add (roomName);
    }

    public void leaveRoom(String userName, String roomName) throws NoSuchUserException, NoSuchRoomException{
        if(!users.containsKey (userName)) throw new NoSuchUserException (userName);
        if(!rooms.containsKey (roomName)) throw new NoSuchRoomException (roomName);
        if(rooms.get (roomName).hasUser (userName)) {
            rooms.get (roomName).removeUser (userName);
            users.get (userName).remove (roomName);
        }
    }

    public void followFriend(String username, String friend_username) throws NoSuchUserException, NoSuchRoomException{
        if(!users.containsKey (friend_username)) throw new NoSuchUserException (username);
        users.get (friend_username).stream ()
                .forEach (rname -> {
                    ChatRoom cr = null;
                    try {
                        cr = getRoom (rname);
                        cr.addUser (username);
                    } catch (NoSuchRoomException ignored) {

                    }
                    users.get (username).add (rname);
                });
    }

}

class NoSuchUserException extends Exception{
    private String name;

    public NoSuchUserException(String name){
        this.name = name;
    }

    public String getMessage(){
        return name;
    }
}

class NoSuchRoomException extends Exception{
    private String name;

    public NoSuchRoomException(String name){
        this.name = name;
    }

    public String getMessage(){
        return name;
    }
}

class ChatSystemTest {

        public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException,
                NoSuchElementException, InvocationTargetException, NoSuchUserException,  NoSuchRoomException {
        Scanner jin = new Scanner(System.in);
        int k = jin.nextInt();
        if ( k == 0 ) {
            ChatRoom cr = new ChatRoom(jin.next());
            int n = jin.nextInt();
            for ( int i = 0 ; i < n ; ++i ) {
                k = jin.nextInt();
                if ( k == 0 ) cr.addUser(jin.next());
                if ( k == 1 ) cr.removeUser(jin.next());
                if ( k == 2 ) System.out.println(cr.hasUser(jin.next()));
            }
            System.out.println("");
            System.out.println(cr.toString());
            n = jin.nextInt();
            if ( n == 0 ) return;
            ChatRoom cr2 = new ChatRoom(jin.next());
            for ( int i = 0 ; i < n ; ++i ) {
                k = jin.nextInt();
                if ( k == 0 ) cr2.addUser(jin.next());
                if ( k == 1 ) cr2.removeUser(jin.next());
                if ( k == 2 ) cr2.hasUser(jin.next());
            }
            System.out.println(cr2.toString());
        }
        if ( k == 1 ) {
            ChatSystem cs = new ChatSystem();
            Method mts[] = cs.getClass().getMethods();
            while ( true ) {
                String cmd = jin.next();
                if ( cmd.equals("stop") ) break;
                if ( cmd.equals("print") ) {
                    System.out.println(cs.getRoom(jin.next())+"\n");continue;
                }
                for ( Method m : mts ) {
                    if ( m.getName().equals(cmd) ) {
                        String params[] = new String[m.getParameterTypes().length];
                        for ( int i = 0 ; i < params.length ; ++i ) params[i] = jin.next();
                        m.invoke(cs,params);
                    }
                }
            }
        }
    }

}

package libary.model;


import exception.PublicationAlreadyExistException;
import exception.UserAlreadyExistException;
import java.io.Serializable;
import java.util.*;

public class Library implements Serializable {

    private Map<String, Publication> publications = new HashMap<>();
    private Map<String, LibraryUser> users = new HashMap<>();

    public Map<String, Publication> getPublications() {
        return publications;
    }

    public Collection<Publication> getSortedPublications(Comparator<Publication> comparator){
        List<Publication> list = new ArrayList<>(this.publications.values());
        list.sort(comparator);
        return list;
    }

    public Optional<Publication> findPublicationsByTitle(String title){
        return Optional.ofNullable(publications.get(title));
    }

    public Map<String, LibraryUser> getUsers() {
        return users;
    }

    public Collection<LibraryUser> getSortedUsers(Comparator<LibraryUser> comparator){
        ArrayList<LibraryUser> list = new ArrayList<>(users.values());
        list.sort(comparator);
        return list;
    }

    public void addPublication(Publication publication){
        if(publications.containsKey(publication.getTitle())){
            throw new PublicationAlreadyExistException("Publikacja o takim tytule juz istnieje: " + publication.getTitle());
        }
        publications.put(publication.getTitle(), publication);
    }

    public void addUser(LibraryUser user){
        if(users.containsKey(user.getPesel()))
            throw new UserAlreadyExistException("Uzytkownik ze wskazanym nr.Pesel juz istnieje: " + user.getPesel());
        users.put(user.getPesel(),user);
    }

    public boolean removePublication(Publication publ){
        if(publications.containsValue(publ)) {
            publications.remove(publ.getTitle());
            return true;
        }else
            return false;
    }

}
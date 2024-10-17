package hallpointer.address.model;

import java.nio.file.Path;
import java.util.List;
import java.util.function.Predicate;

import hallpointer.address.commons.core.GuiSettings;
import hallpointer.address.model.member.Member;
import hallpointer.address.model.session.Session;
import hallpointer.address.model.session.SessionName;
import javafx.collections.ObservableList;

/**
 * The API of the Model component.
 */
public interface Model {
    /**
     * {@code Predicate} that always evaluate to true
     */
    Predicate<Member> PREDICATE_SHOW_ALL_MEMBERS = unused -> true;

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' address book file path.
     */
    Path getAddressBookFilePath();

    /**
     * Sets the user prefs' address book file path.
     */
    void setAddressBookFilePath(Path addressBookFilePath);

    /**
     * Returns the AddressBook
     */
    ReadOnlyAddressBook getAddressBook();

    /**
     * Replaces address book data with the data in {@code addressBook}.
     */
    void setAddressBook(ReadOnlyAddressBook addressBook);

    /**
     * Returns true if a member with the same identity as {@code member} exists in the address book.
     */
    boolean hasMember(Member member);

    /**
     * Deletes the given member.
     * The member must exist in the address book.
     */
    void deleteMember(Member target);

    /**
     * Deletes the given session from the given member.
     * The member must exist in the address book and the
     * session must exist in the member.
     */
    void deleteSession(Member target, SessionName sessionName);

    void deleteSession(Session sessionToDelete);

    List<Session> getSessionList();

    /**
     * Adds the given member.
     * {@code member} must not already exist in the address book.
     */
    void addMember(Member member);

    /**
     * Replaces the given member {@code target} with {@code editedMember}.
     * {@code target} must exist in the address book.
     * The member identity of {@code editedMember} must not be the same as another existing member in the address book.
     */
    void setMember(Member target, Member editedMember);

    /** Returns an unmodifiable view of the filtered member list */
    ObservableList<Member> getFilteredMemberList();

    /**
     * Updates the filter of the filtered member list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredMemberList(Predicate<Member> predicate);

    /**
     * Returns true if a session with the same identity as {@code session} exists in the address book.
     */
    boolean hasSession(Session session);

    /**
     * Adds the given session.
     * {@code session} must not already exist in the address book.
     */
    void addSession(Session session);



    void setSession(Session target, Session editedSession);


}

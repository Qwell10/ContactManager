import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
class ContactManagerTest {

/*
    @Mock
    private Scanner mockScanner;

    @InjectMocks
    private ContactManager contactManager;

    @Test
    public void saveNewContact_happyScenario() {
        when(mockScanner.nextLine())
                .thenReturn("Mike Wazowski")
                .thenReturn("123456789")
                .thenReturn("mike.wazowski@monsters.com");

        String result = contactManager.saveNewContact();

        ContactEntity savedContact = contactManager.contacts.get(0);

        assertEquals("Contact was added.", result);
        assertEquals(1, contactManager.contacts.size());
        assertEquals("Mike Wazowski", savedContact.name());
        assertEquals("123456789", savedContact.phoneNumber());
        assertEquals("mike.wazowski@monsters.com", savedContact.email());
    }

    @Test
    public void saveNewContact_nullInput() {
        when(mockScanner.nextLine())
                .thenReturn(null);

        String result = contactManager.saveNewContact();

        assertEquals("invalid name: " + null, result);
    }

    @Test
    public void saveNewContact_emptyInput() {
        when(mockScanner.nextLine())
                .thenReturn("");

        String result = contactManager.saveNewContact();

        assertEquals("invalid name: " + "", result);
    }

    @Test
    public void findByName_happyScenario() {
        ContactEntity contactEntity = new ContactEntity("Johny Sins", "987654321", "Johny@email.com");
        contactManager.contacts.add(contactEntity);

        when(mockScanner.nextLine()).thenReturn("Johny Sins");

        Optional<ContactEntity> contactResult = contactManager.findByName();

        assertTrue(contactResult.isPresent());
        assertEquals(contactResult.get(), contactEntity);
    }

    @Test
    public void findByName_nameIsBlank() {
        when(mockScanner.nextLine()).thenReturn(" ");

        Optional<ContactEntity> expectedResult = Optional.empty();

        Optional<ContactEntity> result = contactManager.findByName();

        assertEquals(expectedResult, result);
    }


    @Test
    public void deleteByName_happyScenario() {
        ContactEntity contactEntity = new ContactEntity("Jarda", "778", "xx@yy.com");
        contactManager.contacts.add(contactEntity);

        when(mockScanner.nextLine()).thenReturn("Jarda");

        contactManager.deleteByName();

        assertEquals(contactManager.contacts.size(), 0);
    }

    @Test
    public void deleteByName_throwsIllegalArgumentException() {
        when(mockScanner.nextLine()).thenReturn("Jarda");

        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> contactManager.deleteByName());

        assertEquals("Contact with name: Jarda was not found.", thrown.getMessage());
    }
*/
}
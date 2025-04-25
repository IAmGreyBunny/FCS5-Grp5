package enquiries;

/**
 * Controller class responsible for managing Enquiries between users.
 * Uses a doubly linked list structure to store and organise enquiry conversations,
 * allowing replies to be linked to original messages and threads to be traversed.
 */
public class EnquiryController {
    private Enquiry head;
    private Enquiry tail;
    private int nextID = 1;

    /**
     * Creates a new enquiry and appends it to the end of the enquiry list.
     * @param messageText The content of the message.
     * @param senderID    The ID of the sender.
     * @param recipientID The ID of the recipient.
     * @return The created Enquiry object.
     */
    public Enquiry createNewEnquiry(String messageText, String senderID, String recipientID) {
        Enquiry enquiry = new Enquiry(nextID++, messageText, senderID, recipientID);
        if (head == null) {
            head = enquiry;
            tail = enquiry;
        } else {
            tail.next = enquiry;
            enquiry.prev = tail;
            tail = enquiry;
        }
        return enquiry;
    }

    /**
     * Adds a reply to an existing enquiry, inserting it directly after the parent enquiry.
     * @param parentEnquiryID ID of the enquiry being replied to.
     * @param replyText       Content of the reply.
     * @param senderID        ID of the sender replying.
     * @param recipientID     ID of the original sender or intended recipient.
     * @return The created reply Enquiry object, or null if parent not found.
     */
    //reply after given enquiry
    public Enquiry addReply(int parentEnquiryID, String replyText, String senderID, String recipientID) {
        Enquiry parent = findEnquiryByID(parentEnquiryID);
        if (parent == null) return null;

        Enquiry reply = new Enquiry(nextID++, replyText, senderID, recipientID);

        reply.prev = parent;
        reply.next = parent.next;

        if (parent.next != null) {
            parent.next.prev = reply;
        } else {
            tail = reply;
        }
        parent.next = reply;

        return reply;
    }

    /**
     * Searches for an enquiry in the linked list by its ID.
     * @param enquiryID ID of the enquiry to search for.
     * @return The Enquiry object if found, otherwise null.
     */
    //finding enquiry by id
    public Enquiry findEnquiryByID(int enquiryID) {
        Enquiry current = head;
        while (current != null) {
            if (current.getEnquiryID() == enquiryID) return current;
            current = current.next;
        }
        return null;
    }

    /**
     * Displays the full thread of messages starting from a given enquiry ID.
     * Traverses to the start of the thread and prints each message in order.
     * @param enquiryID ID of any enquiry in the thread.
     */
    //display full conversation for given id
    public void displayThreadFrom(int enquiryID) {
        Enquiry start = findEnquiryByID(enquiryID);
        if (start == null) {
            System.out.println("Enquiry not found!");
            return;
        }

        while (start.prev != null) {
            start = start.prev;
        }

        Enquiry current = start;
        while (current != null) {
            System.out.printf("[%s] %s -> %s: %s (ID: %d)\n",
                    current.getCreatedDate(),
                    current.getSenderID(),
                    current.getRecipientID(),
                    current.getMessageText(),
                    current.getEnquiryID()
            );
            current = current.next;
        }
    }

    /**
     * Displays all enquiries in the system.
     * This includes all root enquiries and their replies shown linearly.
     */
    public void displayAllEnquiries() {
        Enquiry current = head;
        while (current != null) {
            System.out.printf("[%d] %s -> %s: %s\n",
                    current.getEnquiryID(),
                    current.getSenderID(),
                    current.getRecipientID(),
                    current.getMessageText()
            );
            current = current.next;
        }
    }
}

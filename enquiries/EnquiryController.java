package enquiries;

public class EnquiryController {
    private Enquiry head;
    private Enquiry tail;
    private int nextID = 1;

    //doubly linked list
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

    //finding enquiry by id
    public Enquiry findEnquiryByID(int enquiryID) {
        Enquiry current = head;
        while (current != null) {
            if (current.getEnquiryID() == enquiryID) return current;
            current = current.next;
        }
        return null;
    }

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

    //this is js for displaying entire list (can remove later)
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

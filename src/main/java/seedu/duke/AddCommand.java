package seedu.duke;

public class AddCommand extends Command {
    private final Client client;

    public AddCommand(Client client) {
        this.client = client;
    }

    public Client getClient() {
        return client;
    }

    @Override
    public void execute(ClientList clients, Ui ui) {
        int NewClientCount = clients.getClientCount() + 1;
        clients.add(client, ui);
        assert NewClientCount == clients.getClientCount();
    }
}

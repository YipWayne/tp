package seedu.duke.commands.clientpackages;

import seedu.duke.TourPlannerException;
import seedu.duke.commands.Command;
import seedu.duke.data.Client;
import seedu.duke.data.ClientPackage;
import seedu.duke.data.Flight;
import seedu.duke.data.Tour;

import java.util.Arrays;

public class AddClientPackageCommand extends Command {
    private ClientPackage clientPackage;
    private String[] rawClientPackage;

    public AddClientPackageCommand(String[] rawClientPackage) {
        this.rawClientPackage = rawClientPackage;
    }

    @Override
    public void execute() {
        try {
            createClientPackage();
            ClientPackage existingClientPackage = clientPackages.getClientPackageById(clientPackage.getId());
            System.out.println("Client package ID already exists. Please try another client package ID.");
        } catch (TourPlannerException e) {
            if (!clientPackage.getClient().equals(null) && !clientPackage.getTour().equals(null)
                    && !clientPackage.getFlight().equals(null)) {
                clientPackages.add(clientPackage);
                ui.showAdd(clientPackage);
            }
        }
    }

    public void executeStorage() {
        try {
            System.out.println(Arrays.toString(rawClientPackage));
            createClientPackage();
            ClientPackage existingClientPackage = clientPackages.getClientPackageById(clientPackage.getId());
            System.out.println("Client package ID already exists. Please try another client package ID.");
        } catch (TourPlannerException e) {
            if (!clientPackage.getClient().equals(null) && !clientPackage.getTour().equals(null)
                    && !clientPackage.getFlight().equals(null)) {
                clientPackages.add(clientPackage);
            }
        }
    }

    public ClientPackage getClientPackage() {
        return clientPackage;
    }

    private void createClientPackage() {
        try {
            String clientPackageId = rawClientPackage[0];
            String clientId = rawClientPackage[1];
            String tourCode = rawClientPackage[2];
            String flightId = rawClientPackage[3];
            Client client = extractClient(clientId);
            Tour tour = extractTour(tourCode);
            Flight flight = extractFlight(flightId);
            clientPackage = new ClientPackage(clientPackageId, client, tour, flight);
        } catch (TourPlannerException e) {
            System.out.println(e.getMessage());
        }
    }

    private Client extractClient(String clientId) throws TourPlannerException {
        return clients.getClientById(clientId);
    }

    private Tour extractTour(String tourId) throws TourPlannerException {
        return tours.getTourById(tourId);
    }

    private Flight extractFlight(String flightId) throws TourPlannerException {
        return flights.getFlightById(flightId);
    }

}

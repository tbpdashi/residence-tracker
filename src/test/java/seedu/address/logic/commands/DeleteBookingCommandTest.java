package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.showResidenceAtIndex;

import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_BOOKING;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_RESIDENCE;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_BOOKING;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_RESIDENCE;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_RESIDENCE;
import static seedu.address.testutil.TypicalResidences.getTypicalResidenceTracker;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class DeleteBookingCommandTest {

    private Model model = new ModelManager(getTypicalResidenceTracker(), new UserPrefs());

    @Test
    public void execute_validResidenceIndexInvalidBookingIndexFilteredList_failure() {
        showResidenceAtIndex(model, INDEX_FIRST_RESIDENCE);
        Index outOfBoundBookingIndex = INDEX_FIRST_BOOKING;
        DeleteBookingCommand deleteBookingCommand =
                new DeleteBookingCommand(INDEX_FIRST_RESIDENCE, outOfBoundBookingIndex);
        assertCommandFailure(deleteBookingCommand, model, Messages.MESSAGE_INVALID_BOOKING_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidResidenceIndexValidBookingIndexFilteredList_failure() {
        showResidenceAtIndex(model, INDEX_SECOND_RESIDENCE);
        Index outOfBoundResidenceIndex = INDEX_THIRD_RESIDENCE;
        assertTrue(outOfBoundResidenceIndex.getZeroBased() < model.getResidenceTracker().getResidenceList().size());
        Index validBookingIndex = INDEX_FIRST_BOOKING;
        assertTrue(outOfBoundResidenceIndex.getZeroBased() < model.getResidenceTracker().getResidenceList().size());
        DeleteBookingCommand deleteBookingCommand =
                new DeleteBookingCommand(INDEX_THIRD_RESIDENCE, validBookingIndex);
        assertCommandFailure(deleteBookingCommand, model, Messages.MESSAGE_INVALID_RESIDENCE_DISPLAYED_INDEX);

    }

    @Test
    public void equals() {
        DeleteBookingCommand deleteBookingFirstCommand = new DeleteBookingCommand(INDEX_FIRST_RESIDENCE, INDEX_FIRST_BOOKING);
        DeleteBookingCommand deleteBookingSecondCommand = new DeleteBookingCommand(INDEX_FIRST_RESIDENCE, INDEX_SECOND_BOOKING);
        DeleteBookingCommand deleteBookingThirdCommand = new DeleteBookingCommand(INDEX_SECOND_RESIDENCE, INDEX_FIRST_BOOKING);

        // same object -> returns true
        assertTrue(deleteBookingFirstCommand.equals(deleteBookingFirstCommand));

        // same values -> returns true
        DeleteBookingCommand deleteBookingFirstCommandCopy = new DeleteBookingCommand(INDEX_FIRST_RESIDENCE, INDEX_FIRST_BOOKING);
        assertTrue(deleteBookingFirstCommand.equals(deleteBookingFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteBookingFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteBookingFirstCommand.equals(null));

        // different booking index -> returns false
        assertFalse(deleteBookingFirstCommand.equals(deleteBookingSecondCommand));

        // different residence index -> returns false
        assertFalse(deleteBookingFirstCommand.equals(deleteBookingThirdCommand));
    }


}

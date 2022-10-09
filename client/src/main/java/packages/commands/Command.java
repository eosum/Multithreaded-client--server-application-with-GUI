package packages.commands;

import packages.util.DataForSending;
import packages.util.Request;
import packages.util.SenderResult;

public interface Command {
    SenderResult getRequest(DataForSending object, Request request);
}
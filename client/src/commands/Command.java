package commands;

import util.DataForSending;
import util.Request;
import util.SenderResult;

public interface Command {
    SenderResult getRequest(DataForSending object, Request request);
}
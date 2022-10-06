package main_package.commands;

import main_package.util.DataForSending;
import main_package.util.Request;
import main_package.util.SenderResult;

public interface Command {
    SenderResult getRequest(DataForSending object, Request request);
}
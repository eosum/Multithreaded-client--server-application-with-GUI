package commands;

import util.Request;

public interface Command {
    Request getRequest(String arg, boolean fromFile);
}
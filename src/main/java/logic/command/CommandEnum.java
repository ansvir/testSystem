package logic.command;

public enum CommandEnum {

    LOGIN {
        {
            this.command = new LoginCommand();
        }
    },
    LOGOUT {
        {
            this.command = new LogoutCommand();
        }
    },
    CREATE_USER {
        {
            this.command = new CreateUserCommand();
        }
    },
    DELETE_USER {
        {
            this.command = new DeleteUserCommand();
        }
    },
    CHOOSE_ACTION {
        {
            this.command = new ChooseActionCommand();
        }
    };

    Command command;
    public Command getCurrentCommand() {
        return command;
    }
}

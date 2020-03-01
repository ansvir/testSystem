package logic.command;

import logic.command.authorization.LoginCommand;
import logic.command.authorization.LogoutCommand;
import logic.command.main.ChooseActionCommand;
import logic.command.tests.CreateTestAboutCommand;
import logic.command.tests.ViewTestsCommand;
import logic.command.tests.show.ChooseCriteriaCommand;
import logic.command.users.AddQuestionCommand;
import logic.command.users.CreateUserCommand;
import logic.command.users.DeleteUserCommand;

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

    CHOOSE_ACTION {
        {
            this.command = new ChooseActionCommand();
        }
    },

    CREATE_TEST_ADD_QUESTION {
        {
            this.command = new AddQuestionCommand();
        }
    },

    CREATE_TEST_MAIN {
        {
            this.command = new CreateTestAboutCommand();
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

    VIEW_TESTS  {
        {
            this.command = new ViewTestsCommand();
        }
    },

    CHOOSE_TESTS_CRITERIA {
        {
            this.command = new ChooseCriteriaCommand();
        }
    },

    ADD_QUESTION {
        {
            this.command = new AddQuestionCommand();
        }
    };

    Command command;

    public Command getCommand() {
        return command;
    }
}

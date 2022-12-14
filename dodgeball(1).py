import random

def get_computer_action(num_player_balls, num_opponent_balls):
    if num_player_balls >= 2:
        return 'dodge'

    if num_opponent_balls == 0:
        if random.randint(1,2) == 1:
            return 'dodge'
        else:
            return 'pickup'

    if num_opponent_balls == 3:
        if random.randint(1, 2) == 1:
            return 'dodge'
        else:
            return 'throw'

    if num_opponent_balls == 1 or num_opponent_balls ==2:
        x = random.randint(1,3)
        if x == 1:
            return 'dodge'
        elif x == 2:
            return 'pickup'
        else:
            return 'throw'



def get_human_action(num_player_balls, num_opponent_balls):
    while True:
        human_action = input('Please enter your action: ')
        if human_action == 'dodge':
            return 'dodge'
        elif human_action == 'pickup':
            if num_player_balls == 3:
                human_action = input('Please enter your action: ')
            else:
                return 'pickup'
        elif human_action == 'throw' and num_player_balls > 0:
            return 'throw'


def point_gained(human_action, computer_action):
    if human_action == "throw" and computer_action == "throw":
        return 'human and computer'
    elif human_action == "pickup" and computer_action == "throw":
        return 'computer'
    elif human_action == "throw" and computer_action == "pickup":
        return 'human'
    else:
        return 'no one'

def play_game():
    num_player_balls = 0
    num_computer_balls = 0
    computer_point = 0
    human_point = 0
    while computer_point < 5 and human_point < 5:
        computer_action = get_computer_action(num_player_balls, num_computer_balls)
        if computer_action == 'pickup':
            num_computer_balls += 1
        elif computer_action == 'throw':
            num_computer_balls -= 1

        human_action = get_human_action(num_player_balls, num_computer_balls)
        if human_action == 'pickup':
            num_player_balls += 1
        elif human_action == 'throw':
            num_player_balls -= 1

        print('\nComputer\' action:', computer_action)
        print('Human\' action:', human_action)


        win = point_gained(human_action, computer_action)
        print(win, 'has win this turn')
        if win == 'human and computer':
            computer_point += 1
            human_point += 1
        elif win == 'computer':
            computer_point += 1
        elif win == 'human':
            human_point += 1

        print('Now the score for computer is',computer_point)
        print('The score for human is',human_point,'\n')

        print('The number of balls computer has is', num_computer_balls)
        print('The number of balls human has is', num_player_balls,'\n')

    if computer_point == 5:
        print('\nCongratulation, computer has won this game')
    else:
        print('Congratulation, human has won this game')


play_game()




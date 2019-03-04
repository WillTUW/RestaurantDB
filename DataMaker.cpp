//
// Created by Will on 3/4/2019.
//

#include <iostream>

void toUser(int UserID, std::string Name, int RID){
    std::cout << "INSERT INTO USER VALUES( " << UserID << ", " << Name << ", " << RID << " );";
}

void UserMaker(){
    for(int i = 0; i < 30; i++){
        std::string toString = std::to_string(i);
        std::string Name = "User" + toString;
        toUser(i, Name, i);
    }
}

main(){
    UserMaker();
}




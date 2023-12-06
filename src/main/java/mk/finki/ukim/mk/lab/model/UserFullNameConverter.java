package mk.finki.ukim.mk.lab.model;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class UserFullNameConverter implements AttributeConverter<UserFullName, String> {

    private static final String SEPARATOR = ", ";
    @Override
    public String convertToDatabaseColumn(UserFullName userFullname) {
        if(userFullname == null)
            return null;

        StringBuilder sb = new StringBuilder();
        if(userFullname.getSurname() != null
                && !userFullname.getSurname().isEmpty()){
            sb.append(userFullname.getSurname());
            sb.append(SEPARATOR);
        }

        if(userFullname.getName() != null
                && !userFullname.getName().isEmpty()){
            sb.append(userFullname.getName());
        }

        return sb.toString();
    }

    @Override
    public UserFullName convertToEntityAttribute(String s) {
        if(s == null || s.isEmpty())
            return null;

        String []pieces = s.split(SEPARATOR);

        if(pieces == null || pieces.length == 0)
            return null;

        UserFullName fullname = new UserFullName();
        String firstPiece = !pieces[0].isEmpty() ? pieces[0] : null;

        if(s.contains(SEPARATOR)){
            fullname.setSurname(firstPiece);

            if(pieces.length >= 2 && pieces[1] != null){
                fullname.setName(pieces[1]);
            }
        }
        else {
            fullname.setName(firstPiece);
        }

        return fullname;
    }
}

package pl.training.bank.common;

import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public class Mapper {

    public <Source, Destination> void map(Source source, Destination destination) {
        new ModelMapper().map(source, destination);
    }

    public <Source, DestinationType> DestinationType map(Source source, Class<DestinationType> type) {
        return new ModelMapper().map(source, type);
    }

    public <SourceElement, DestinationType> List<DestinationType> map(List<SourceElement> source, Class<DestinationType> type) {
        ModelMapper modelMapper = new ModelMapper();
        return source.stream()
                .map(element -> modelMapper.map(element, type))
                .collect(Collectors.toList());
    }

}

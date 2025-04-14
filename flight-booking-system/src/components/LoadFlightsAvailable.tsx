import FlightDetails, { IFlightDetails } from "./FlightDetails";

export interface IFlightsData {
  flights: null | IFlightDetails[];
}

export default function LoadFlightsAvailable({ flights }: IFlightsData) {
  return (
    <>
      {flights?.map((flightDetails, index) => (
        <FlightDetails key={index} {...flightDetails} />
      ))}
    </>
  );
}

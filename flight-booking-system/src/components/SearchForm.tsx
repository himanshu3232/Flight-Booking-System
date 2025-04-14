import TextField from "@mui/material/TextField";
import Autocomplete from "@mui/material/Autocomplete";
import airports from "../static/airports";
import { useState } from "react";
import axios from "axios";
import LoadFlightsAvailable from "./LoadFlightsAvailable";
import { IFlightDetails } from "./FlightDetails";

export default function SearchForm() {
  const [from, setFrom] = useState<string>("");
  const [to, setTo] = useState<string>("");
  const [flightsData, setFlightsData] = useState<null | IFlightDetails[]>(null);

  const handleSearch = async () => {
    const fromAirport = airports.find(
      (airport) => airport.text === from
    )?.value;
    const toAirport = airports.find((airport) => airport.text === to)?.value;
    const flightsData = await axios
      .get("http://localhost:8072/search-service/flights/search", {
        params: {
          from: fromAirport,
          to: toAirport,
        },
      })
      .then((response) => response.data)
      .catch((_) => alert("Failed to fetch flight details!"));

    setFlightsData(flightsData);
  };

  return (
    <>
      <div className="d-flex justify-content-center gap-3">
        <Autocomplete
          disablePortal
          options={airports.map((airport) => airport.text)}
          value={from}
          onChange={(_, newValue) => setFrom(newValue || "")}
          sx={{ width: 250 }}
          renderInput={(params) => <TextField {...params} label="From" />}
        />
        <Autocomplete
          disablePortal
          options={airports.map((airport) => airport.text)}
          value={to}
          onChange={(_, newValue) => setTo(newValue || "")}
          sx={{ width: 250 }}
          renderInput={(params) => <TextField {...params} label="To" />}
        />
      </div>

      <div className="text-center mt-4">
        <button
          className="btn btn-primary"
          onClick={handleSearch}
          disabled={!from || !to || from == to}
        >
          Search
        </button>
      </div>

      <div className="text-center mt-2 text-muted">
        {from && to && `${from} ➡️ ${to}`}
      </div>
      {flightsData && flightsData.length > 0 && (
        <LoadFlightsAvailable flights={flightsData} />
      )}
    </>
  );
}

import { Button } from "@mui/material";
import { useNavigate } from "react-router-dom";

export default function UnAuthorized() {
  const navigate = useNavigate()
  return (
    <>
      <h1 className="display-1">401 UnAuthorized</h1>
      <Button onClick={() => navigate("/")}>Return to login page</Button>
    </>
  );
}

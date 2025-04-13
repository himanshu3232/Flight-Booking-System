import { Outlet } from 'react-router-dom'
import Dashboard from './Dashboard'

export default function AppLayout() {
  return (
    <>
      <Dashboard/>
      <Outlet/>
    </>
  )
}

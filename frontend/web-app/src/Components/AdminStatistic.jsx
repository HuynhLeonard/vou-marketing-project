import { useEffect } from "react"

import LineChart from "./LineChart";
import PieChart from "./PieChart"

// const DynamicLineChart = import("./LineChart")
// const DynamicPieChart = import("./PieChart")

export default function AdminStatistic({listNumUsers}) {

  const dataThisYear = [5, 5, 8, 10, 15, 15, 15];
  const dataLastYear = [1, 2, 3, 3, 4, 5, 5];

  return (
    <div className="flex flex-row overscroll-y-none">
      <div className="w-3/5 bg-white shadow-md rounded-lg p-4 mr-5">
        <LineChart dataThisYear={dataThisYear} dataLastYear={dataLastYear} />
      </div>
      <div className="w-2/5 bg-white shadow-md rounded-lg">
        <PieChart data={listNumUsers}/>
      </div>
    </div>
  )
}
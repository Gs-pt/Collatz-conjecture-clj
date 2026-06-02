(ns collatz.core
  (:gen-class)
  )
(defn collatz [start]
  (letfn [(step [n]
            (cond
              (even? n) (/ n 2)
              (= n 1) nil
              :else (inc (* n 3))))]
    (->>
     (iterate step start)
     (take-while identity))))
(defn -main
  [& args]
  (print "Choose starting number: ")
  (flush)
  (let [x (collatz (Integer/parseInt (read-line)))]
    (spit (str "collatz" (nth x 0) ".csv") "Step, Value \n")
    (println "Step, Value")
    (dotimes [n (- (count x) 1)]
      (spit (str "collatz" (nth x 0) ".csv") (+ n 1) :append true)
      (print (+ n 1))
      (flush)
      (spit (str "collatz" (nth x 0) ".csv") ", " :append true) 
      (print ", ")
      (flush)
      (spit (str "collatz" (nth x 0) ".csv") (nth x n) :append true)
      (println (nth x n))
      (spit (str "collatz" (nth x 0) ".csv") "\n" :append true))))

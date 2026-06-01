(ns collatz.core
  (:gen-class)
  )
  (def originalNum (atom nil)
    )
  (def name-of-file (atom nil))
  (def counter (atom 0))
  (defn collatz-step [] 
  (swap! originalNum
        (fn [n]
          (cond
            (nil? n) n
            (= n 1) 1
            (even? n) (/ n 2)
            :else (+ (* 3 n) 1)))))

(defn -main
  [& args]
  (print "Choose starting number: ")
  (flush)
  (let [n (Integer/parseInt (read-line))] 
    (reset! originalNum n)
    ) 
  
  (println "Press enter to see list and save as csv file")
  (read-line)
  (reset! name-of-file (str "Collatz" @originalNum ".csv"))
  (println "Step, Value") 
  (spit @name-of-file "Step, Value \n")
  (loop [] 
    (swap! counter inc)
    (print @counter)
    (spit @name-of-file @counter :append true)
    (flush)
    (print ", ")
    (spit @name-of-file ", " :append true)
    (flush)
    (println @originalNum)
    (spit @name-of-file @originalNum :append true)
    (spit @name-of-file "\n" :append true)
    (when (and @originalNum (not= @originalNum 1))
      (collatz-step)
      (recur)))
  (print "Press any key to continue...") 
  (flush)
  (read-line)
  )

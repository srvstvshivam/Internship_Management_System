import { useState } from "react";
import { z } from "zod";
import { Eye, EyeOff, Mail, Lock, ArrowRight, Check } from "lucide-react";

const loginSchema = z.object({
  email: z
    .string()
    .trim()
    .nonempty("Email is required")
    .email("Please enter a valid email address")
    .max(255, "Email must be less than 255 characters"),
  password: z
    .string()
    .nonempty("Password is required")
    .min(8, "Password must be at least 8 characters")
    .max(50, "Password must be less than 50 characters"),
});

const Coordinator = () => {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [showPassword, setShowPassword] = useState(false);
  const [errors, setErrors] = useState({});
  const [isLoading, setIsLoading] = useState(false);

 const handleSubmit = (e) => {
  e.preventDefault();

  const result = loginSchema.safeParse({
    email: email.trim(),
    password: password.trim(),
  });

  if (!result.success) {
    const fieldErrors = result.error.flatten().fieldErrors;

    setErrors({
      email: fieldErrors.email?.[0],
      password: fieldErrors.password?.[0],
    });

    return;
  }

  setErrors({});
  setIsLoading(true);
  setTimeout(() => setIsLoading(false), 2000);
};

  return (
  <div className="min-h-screen w-full flex items-center justify-center bg-gradient-to-br from-indigo-50 via-white to-purple-50 px-4">
    
    <div className="w-full max-w-md bg-white/80 backdrop-blur-xl shadow-2xl rounded-2xl p-8 sm:p-10 border border-gray-100">
      
  


      <form onSubmit={handleSubmit} className="space-y-6" noValidate>
      
        <div className="space-y-2">
          <label className="text-sm font-medium text-gray-700">
            Email
          </label>

          <div className="relative">
            <Mail className="absolute left-4 top-1/2 -translate-y-1/2 w-4 h-4 text-gray-400" />
            
            <input
              type="email"
              value={email}
              onChange={(e) => setEmail(e.target.value)}
              placeholder="you@example.com"
              className={`w-full h-12 pl-11 pr-4 rounded-xl border text-sm bg-white focus:outline-none focus:ring-2 transition-all duration-200 ${
                errors.email
                  ? "border-red-500 focus:ring-red-500"
                  : "border-gray-200 focus:ring-indigo-500"
              }`}
            />
          </div>

          {errors.email && (
            <p className="text-red-500 text-xs">{errors.email}</p>
          )}
        </div>

        
        <div className="space-y-2">
          <div className="flex items-center justify-between">
            <label className="text-sm font-medium text-gray-700">
              Password
            </label>
            <button
              type="button"
              className="text-xs text-indigo-600 hover:underline"
            >
              Forgot password?
            </button>
          </div>

          <div className="relative">
            <Lock className="absolute left-4 top-1/2 -translate-y-1/2 w-4 h-4 text-gray-400" />

            <input
              type={showPassword ? "text" : "password"}
              value={password}
              onChange={(e) => setPassword(e.target.value)}
              placeholder="••••••••"
              className={`w-full h-12 pl-11 pr-12 rounded-xl border text-sm bg-white focus:outline-none focus:ring-2 transition-all duration-200 ${
                errors.password
                  ? "border-red-500 focus:ring-red-500"
                  : "border-gray-200 focus:ring-indigo-500"
              }`}
            />

            <button
              type="button"
              onClick={() => setShowPassword(!showPassword)}
              className="absolute right-4 top-1/2 -translate-y-1/2 text-gray-400 hover:text-gray-700 transition-colors"
            >
              {showPassword ? (
                <EyeOff className="w-4 h-4" />
              ) : (
                <Eye className="w-4 h-4" />
              )}
            </button>
          </div>

          {errors.password && (
            <p className="text-red-500 text-xs">{errors.password}</p>
          )}
        </div>

   
        <button
          type="submit"
          disabled={isLoading}
          className="w-full h-12 rounded-xl bg-blue-950 text-white font-semibold text-sm flex items-center justify-center gap-2 hover:shadow-lg  active:scale-[0.98] transition-all duration-200 disabled:opacity-60"
        >
          {isLoading ? (
            <div className="w-5 h-5 border-2 border-white/30 border-t-white rounded-full animate-spin" />
          ) : (
            <>
              Sign in
              <ArrowRight className="w-4 h-4" />
            </>
          )}
        </button>
      </form>

   
      <p className="text-center text-sm text-gray-500 mt-8">
        Don’t have an account?{" "}
        <button className="text-indigo-600 font-medium hover:underline">
          Create account
        </button>
      </p>
    </div>
  </div>
);
};

export default Coordinator;

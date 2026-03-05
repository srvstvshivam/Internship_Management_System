import React, { useState, useEffect } from 'react';
import { 
  Users, CalendarCheck, TrendingUp, GraduationCap, 
  Server, RefreshCw, Loader2, Save, UploadCloud, 
  CheckCircle, XCircle, Search, Bell, Menu, MoreVertical,
  History, AlertCircle
} from 'lucide-react';

const API_BASE = 'http://localhost:8083/api/mentors';

// --- UI Components ---

const Badge = ({ children, variant = 'default' }) => {
  const variants = {
    default: 'bg-slate-100 text-slate-700 border-slate-200',
    success: 'bg-emerald-50 text-emerald-700 border-emerald-200',
    warning: 'bg-amber-50 text-amber-700 border-amber-200',
    danger: 'bg-red-50 text-red-700 border-red-200',
    info: 'bg-blue-50 text-blue-700 border-blue-200',
    indigo: 'bg-indigo-50 text-indigo-700 border-indigo-200',
  };
  return (
    <span className={`inline-flex items-center px-2.5 py-0.5 rounded-full text-xs font-medium border ${variants[variant]}`}>
      {children}
    </span>
  );
};

const Avatar = ({ name }) => {
  const initials = name ? name.split(' ').map(n => n[0]).join('').toUpperCase() : '?';
  return (
    <div className="h-8 w-8 rounded-full bg-indigo-100 flex items-center justify-center text-indigo-600 font-semibold text-xs ring-2 ring-white">
      {initials}
    </div>
  );
};

// --- Sub-Component: Assigned Students ---
const AssignedStudents = ({ mentorId }) => {
  const [students, setStudents] = useState([]);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);

  const fetchStudents = async () => {
    setLoading(true);
    setError(null);
    try {
      const response = await fetch(`${API_BASE}/${mentorId}/students`);
      if (!response.ok) throw new Error('Failed to fetch data');
      const data = await response.json();
      setStudents(data);
    } catch (err) {
      setError('Unable to connect to the backend server. Please ensure Spring Boot is running on port 8083.');
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    fetchStudents();
  }, [mentorId]);

  return (
    <div className="space-y-6 animate-in fade-in duration-500">
      <div className="flex flex-col sm:flex-row justify-between items-start sm:items-center gap-4">
        <div>
          <h3 className="text-lg font-semibold text-slate-900">Assigned Students</h3>
          <p className="text-sm text-slate-500">Manage and view the students currently under your supervision.</p>
        </div>
        <button 
          onClick={fetchStudents} 
          disabled={loading}
          className="inline-flex items-center gap-2 px-4 py-2 text-sm font-medium text-slate-700 bg-white border border-slate-300 rounded-lg shadow-sm hover:bg-slate-50 focus:outline-none focus:ring-2 focus:ring-indigo-500 focus:ring-offset-2 disabled:opacity-50 transition-all"
        >
          <RefreshCw className={`w-4 h-4 ${loading ? 'animate-spin' : ''}`} /> 
          {loading ? 'Refreshing...' : 'Refresh Data'}
        </button>
      </div>

      <div className="bg-white rounded-xl shadow-sm border border-slate-200 overflow-hidden">
        <div className="p-4 border-b border-slate-200 bg-slate-50/50">
          <div className="relative max-w-md">
            <Search className="absolute left-3 top-1/2 -translate-y-1/2 w-4 h-4 text-slate-400" />
            <input 
              type="text" 
              placeholder="Search students by name or email..." 
              className="w-full pl-9 pr-4 py-2 text-sm border border-slate-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-indigo-500/20 focus:border-indigo-500 transition-all"
            />
          </div>
        </div>

        <div className="overflow-x-auto">
          <table className="w-full text-left border-collapse">
            <thead>
              <tr className="bg-slate-50 border-b border-slate-200 text-slate-500 text-xs uppercase tracking-wider">
                <th className="px-6 py-4 font-semibold">Student</th>
                <th className="px-6 py-4 font-semibold">Contact Info</th>
                <th className="px-6 py-4 font-semibold">Enrollment No.</th>
                <th className="px-6 py-4 font-semibold">Status</th>
                <th className="px-6 py-4 font-semibold text-right">Actions</th>
              </tr>
            </thead>
            <tbody className="divide-y divide-slate-100">
              {loading ? (
                [1, 2, 3].map((i) => (
                  <tr key={i} className="animate-pulse">
                    <td className="px-6 py-4"><div className="flex items-center gap-3"><div className="w-8 h-8 bg-slate-200 rounded-full"></div><div className="h-4 w-24 bg-slate-200 rounded"></div></div></td>
                    <td className="px-6 py-4"><div className="h-4 w-32 bg-slate-200 rounded"></div></td>
                    <td className="px-6 py-4"><div className="h-4 w-20 bg-slate-200 rounded"></div></td>
                    <td className="px-6 py-4"><div className="h-5 w-16 bg-slate-200 rounded-full"></div></td>
                    <td className="px-6 py-4"><div className="h-4 w-8 bg-slate-200 rounded ml-auto"></div></td>
                  </tr>
                ))
              ) : error ? (
                <tr>
                  <td colSpan="5" className="px-6 py-12 text-center">
                    <div className="inline-flex items-center justify-center w-12 h-12 rounded-full bg-red-100 mb-4">
                      <AlertCircle className="w-6 h-6 text-red-600" />
                    </div>
                    <h3 className="text-sm font-medium text-slate-900 mb-1">Connection Error</h3>
                    <p className="text-sm text-red-500">{error}</p>
                  </td>
                </tr>
              ) : students.length === 0 ? (
                <tr>
                  <td colSpan="5" className="px-6 py-12 text-center">
                    <div className="inline-flex items-center justify-center w-12 h-12 rounded-full bg-slate-100 mb-4">
                      <Users className="w-6 h-6 text-slate-400" />
                    </div>
                    <h3 className="text-sm font-medium text-slate-900 mb-1">No Students Found</h3>
                    <p className="text-sm text-slate-500">You don't have any students assigned to you yet.</p>
                  </td>
                </tr>
              ) : (
                students.map(s => (
                  <tr key={s.id} className="hover:bg-slate-50/80 transition-colors group">
                    <td className="px-6 py-4">
                      <div className="flex items-center gap-3">
                        <Avatar name={`${s.firstName} ${s.lastName}`} />
                        <div>
                          <p className="text-sm font-medium text-slate-900">{s.firstName} {s.lastName}</p>
                          <p className="text-xs text-slate-500">ID: #{s.id}</p>
                        </div>
                      </div>
                    </td>
                    <td className="px-6 py-4">
                      <p className="text-sm text-slate-600">{s.email}</p>
                    </td>
                    <td className="px-6 py-4">
                      <p className="text-sm font-medium text-slate-700">{s.enrollmentNumber || 'N/A'}</p>
                    </td>
                    <td className="px-6 py-4">
                      <Badge variant="success">Active</Badge>
                    </td>
                    <td className="px-6 py-4 text-right">
                      {/* Action button disabled per instructions - Admin only */}
                      <button className="text-slate-300 cursor-not-allowed p-1 rounded-md" title="Only Admin can edit/delete">
                        <MoreVertical className="w-4 h-4" />
                      </button>
                    </td>
                  </tr>
                ))
              )}
            </tbody>
          </table>
        </div>
      </div>
    </div>
  );
};

// --- Sub-Component: Attendance Form with History ---
const AttendanceForm = ({ mentorId, showToast }) => {
  const [formData, setFormData] = useState({
    studentId: '',
    date: new Date().toISOString().split('T')[0],
    status: 'PRESENT',
    remarks: ''
  });
  const [isSubmitting, setIsSubmitting] = useState(false);
  
  // History State
  const [history, setHistory] = useState([]);
  const [loadingHistory, setLoadingHistory] = useState(false);

  // Fetch History automatically when Student ID changes (Debounced)
  useEffect(() => {
    const fetchHistory = async () => {
      if (!formData.studentId) {
        setHistory([]);
        return;
      }
      setLoadingHistory(true);
      try {
        const response = await fetch(`${API_BASE}/${mentorId}/students/${formData.studentId}/attendance`);
        if (response.ok) {
          const data = await response.json();
          // Sort by date descending (newest first)
          setHistory(data.sort((a, b) => new Date(b.attendanceDate) - new Date(a.attendanceDate)));
        }
      } catch (error) {
        console.error("Failed to fetch history", error);
      } finally {
        setLoadingHistory(false);
      }
    };

    const delayDebounceFn = setTimeout(() => {
      fetchHistory();
    }, 500); // Wait 500ms after typing stops

    return () => clearTimeout(delayDebounceFn);
  }, [formData.studentId, mentorId]);

  const handleSubmit = async (e) => {
    e.preventDefault();
    setIsSubmitting(true);
    try {
      const response = await fetch(`${API_BASE}/${mentorId}/attendance`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(formData)
      });
      if (!response.ok) throw new Error('Failed to save attendance');
      showToast('Attendance recorded successfully!', 'success');
      
      // Update history table immediately by fetching again
      const historyRes = await fetch(`${API_BASE}/${mentorId}/students/${formData.studentId}/attendance`);
      if (historyRes.ok) {
        const data = await historyRes.json();
        setHistory(data.sort((a, b) => new Date(b.attendanceDate) - new Date(a.attendanceDate)));
      }
      
      setFormData({ ...formData, remarks: '' }); // Clear remarks but keep student ID
    } catch (error) {
      showToast(error.message, 'error');
    } finally {
      setIsSubmitting(false);
    }
  };

  const getStatusBadge = (status) => {
    switch (status) {
      case 'PRESENT': return <Badge variant="success">Present</Badge>;
      case 'ABSENT': return <Badge variant="danger">Absent</Badge>;
      case 'ON_LEAVE': return <Badge variant="warning">On Leave</Badge>;
      default: return <Badge>{status}</Badge>;
    }
  };

  return (
    <div className="max-w-4xl mx-auto animate-in fade-in duration-500 space-y-8">
      {/* Form Section */}
      <div className="bg-white rounded-2xl shadow-sm border border-slate-200 overflow-hidden">
        <div className="px-6 py-4 border-b border-slate-100 bg-slate-50/50 flex items-center gap-2">
          <CalendarCheck className="w-5 h-5 text-indigo-500" />
          <h4 className="font-medium text-slate-800">Record Daily Attendance</h4>
        </div>
        
        <form onSubmit={handleSubmit} className="p-6 space-y-6">
          <div className="grid grid-cols-1 sm:grid-cols-2 gap-6">
            <div className="space-y-1.5">
              <label className="block text-sm font-medium text-slate-700">Student ID <span className="text-red-500">*</span></label>
              <input 
                type="number" required 
                value={formData.studentId} 
                onChange={e => setFormData({...formData, studentId: e.target.value})} 
                className="w-full px-4 py-2.5 text-sm border border-slate-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-indigo-500/20 focus:border-indigo-500 transition-all placeholder:text-slate-400"
                placeholder="Type ID to load history (e.g. 1)"
              />
            </div>
            <div className="space-y-1.5">
              <label className="block text-sm font-medium text-slate-700">Date <span className="text-red-500">*</span></label>
              <input 
                type="date" required 
                value={formData.date} 
                onChange={e => setFormData({...formData, date: e.target.value})} 
                className="w-full px-4 py-2.5 text-sm border border-slate-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-indigo-500/20 focus:border-indigo-500 transition-all text-slate-700"
              />
            </div>
          </div>

          <div className="space-y-1.5">
            <label className="block text-sm font-medium text-slate-700">Status <span className="text-red-500">*</span></label>
            <select 
              required 
              value={formData.status} 
              onChange={e => setFormData({...formData, status: e.target.value})} 
              className="w-full px-4 py-2.5 text-sm border border-slate-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-indigo-500/20 focus:border-indigo-500 transition-all bg-white text-slate-700"
            >
              <option value="PRESENT">Present</option>
              <option value="ABSENT">Absent</option>
              <option value="ON_LEAVE">On Leave</option>
            </select>
          </div>

          <div className="space-y-1.5">
            <label className="block text-sm font-medium text-slate-700">Remarks <span className="text-slate-400 font-normal">(Optional)</span></label>
            <textarea 
              rows="2" 
              value={formData.remarks} 
              onChange={e => setFormData({...formData, remarks: e.target.value})} 
              className="w-full px-4 py-3 text-sm border border-slate-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-indigo-500/20 focus:border-indigo-500 transition-all placeholder:text-slate-400"
              placeholder="Add any additional notes about attendance..."
            ></textarea>
          </div>

          <div className="pt-2 flex justify-end">
            <button 
              type="submit" 
              disabled={isSubmitting || !formData.studentId}
              className="w-full sm:w-auto px-6 py-2.5 bg-indigo-600 text-white text-sm font-medium rounded-lg hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-indigo-500 focus:ring-offset-2 transition-all disabled:opacity-70 flex justify-center items-center gap-2"
            >
              {isSubmitting ? <Loader2 className="w-4 h-4 animate-spin" /> : <Save className="w-4 h-4" />}
              {isSubmitting ? 'Saving...' : 'Save Attendance Record'}
            </button>
          </div>
        </form>
      </div>

      {/* History Section */}
      <div className="bg-white rounded-2xl shadow-sm border border-slate-200 overflow-hidden">
        <div className="px-6 py-4 border-b border-slate-100 bg-slate-50/50 flex justify-between items-center">
          <div className="flex items-center gap-2">
            <History className="w-5 h-5 text-slate-400" />
            <h4 className="font-medium text-slate-800">Attendance History</h4>
          </div>
          {formData.studentId && <Badge variant="indigo">Student ID: {formData.studentId}</Badge>}
        </div>
        
        <div className="overflow-x-auto">
          <table className="w-full text-left border-collapse">
            <thead>
              <tr className="bg-slate-50 border-b border-slate-200 text-slate-500 text-xs uppercase tracking-wider">
                <th className="px-6 py-3 font-semibold">Date</th>
                <th className="px-6 py-3 font-semibold">Status</th>
                <th className="px-6 py-3 font-semibold">Remarks</th>
              </tr>
            </thead>
            <tbody className="divide-y divide-slate-100">
              {!formData.studentId ? (
                <tr><td colSpan="3" className="px-6 py-8 text-center text-sm text-slate-400">Enter a Student ID above to view history.</td></tr>
              ) : loadingHistory ? (
                <tr><td colSpan="3" className="px-6 py-8 text-center"><Loader2 className="w-5 h-5 animate-spin mx-auto text-indigo-500" /></td></tr>
              ) : history.length === 0 ? (
                <tr><td colSpan="3" className="px-6 py-8 text-center text-sm text-slate-400">No attendance records found for this student.</td></tr>
              ) : (
                history.map(record => (
                  <tr key={record.id} className="hover:bg-slate-50/50 transition-colors">
                    <td className="px-6 py-3 text-sm text-slate-700 font-medium">{record.attendanceDate}</td>
                    <td className="px-6 py-3 text-sm">{getStatusBadge(record.status)}</td>
                    <td className="px-6 py-3 text-sm text-slate-500">{record.remarks || '-'}</td>
                  </tr>
                ))
              )}
            </tbody>
          </table>
        </div>
      </div>
    </div>
  );
};

// --- Sub-Component: Progress Form with History ---
const ProgressForm = ({ mentorId, showToast }) => {
  const [formData, setFormData] = useState({
    studentId: '',
    weekNumber: '',
    taskDescription: '',
    completionStatus: 'COMPLETED',
    remarks: ''
  });
  const [isSubmitting, setIsSubmitting] = useState(false);

  // History State
  const [history, setHistory] = useState([]);
  const [loadingHistory, setLoadingHistory] = useState(false);

  // Fetch History automatically when Student ID changes (Debounced)
  useEffect(() => {
    const fetchHistory = async () => {
      if (!formData.studentId) {
        setHistory([]);
        return;
      }
      setLoadingHistory(true);
      try {
        const response = await fetch(`${API_BASE}/${mentorId}/students/${formData.studentId}/progress`);
        if (response.ok) {
          const data = await response.json();
          // Sort by week number descending
          setHistory(data.sort((a, b) => b.weekNumber - a.weekNumber));
        }
      } catch (error) {
        console.error("Failed to fetch history", error);
      } finally {
        setLoadingHistory(false);
      }
    };

    const delayDebounceFn = setTimeout(() => {
      fetchHistory();
    }, 500);

    return () => clearTimeout(delayDebounceFn);
  }, [formData.studentId, mentorId]);

  const handleSubmit = async (e) => {
    e.preventDefault();
    setIsSubmitting(true);
    try {
      const response = await fetch(`${API_BASE}/${mentorId}/progress`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ ...formData, weekNumber: parseInt(formData.weekNumber) })
      });
      if (!response.ok) throw new Error('Failed to save progress');
      showToast('Weekly progress updated successfully!', 'success');
      
      // Update history table immediately
      const historyRes = await fetch(`${API_BASE}/${mentorId}/students/${formData.studentId}/progress`);
      if (historyRes.ok) {
        const data = await historyRes.json();
        setHistory(data.sort((a, b) => b.weekNumber - a.weekNumber));
      }

      setFormData({ ...formData, taskDescription: '', remarks: '' }); // keep ID and week
    } catch (error) {
      showToast(error.message, 'error');
    } finally {
      setIsSubmitting(false);
    }
  };

  const getStatusBadge = (status) => {
    switch (status) {
      case 'COMPLETED': return <Badge variant="success">Completed</Badge>;
      case 'IN_PROGRESS': return <Badge variant="info">In Progress</Badge>;
      case 'PENDING': return <Badge variant="warning">Pending</Badge>;
      default: return <Badge>{status}</Badge>;
    }
  };

  return (
    <div className="max-w-4xl mx-auto animate-in fade-in duration-500 space-y-8">
      {/* Form Section */}
      <div className="bg-white rounded-2xl shadow-sm border border-slate-200 overflow-hidden">
        <div className="px-6 py-4 border-b border-slate-100 bg-slate-50/50 flex items-center gap-2">
          <TrendingUp className="w-5 h-5 text-indigo-500" />
          <h4 className="font-medium text-slate-800">Progress Report Entry</h4>
        </div>
        
        <form onSubmit={handleSubmit} className="p-6 space-y-6">
          <div className="grid grid-cols-1 sm:grid-cols-2 gap-6">
            <div className="space-y-1.5">
              <label className="block text-sm font-medium text-slate-700">Student ID <span className="text-red-500">*</span></label>
              <input 
                type="number" required 
                value={formData.studentId} 
                onChange={e => setFormData({...formData, studentId: e.target.value})} 
                className="w-full px-4 py-2.5 text-sm border border-slate-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-indigo-500/20 focus:border-indigo-500 transition-all placeholder:text-slate-400"
                placeholder="Type ID to load history (e.g. 1)"
              />
            </div>
            <div className="space-y-1.5">
              <label className="block text-sm font-medium text-slate-700">Week Number <span className="text-red-500">*</span></label>
              <input 
                type="number" min="1" max="52" required 
                value={formData.weekNumber} 
                onChange={e => setFormData({...formData, weekNumber: e.target.value})} 
                className="w-full px-4 py-2.5 text-sm border border-slate-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-indigo-500/20 focus:border-indigo-500 transition-all"
                placeholder="e.g. 4"
              />
            </div>
          </div>

          <div className="space-y-1.5">
            <label className="block text-sm font-medium text-slate-700">Task Description <span className="text-red-500">*</span></label>
            <input 
              type="text" required 
              placeholder="e.g., Implemented JWT Authentication for User Login" 
              value={formData.taskDescription} 
              onChange={e => setFormData({...formData, taskDescription: e.target.value})} 
              className="w-full px-4 py-2.5 text-sm border border-slate-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-indigo-500/20 focus:border-indigo-500 transition-all placeholder:text-slate-400"
            />
          </div>

          <div className="space-y-1.5">
            <label className="block text-sm font-medium text-slate-700">Completion Status <span className="text-red-500">*</span></label>
            <select 
              required 
              value={formData.completionStatus} 
              onChange={e => setFormData({...formData, completionStatus: e.target.value})} 
              className="w-full px-4 py-2.5 text-sm border border-slate-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-indigo-500/20 focus:border-indigo-500 transition-all bg-white"
            >
              <option value="COMPLETED">Completed</option>
              <option value="IN_PROGRESS">In Progress</option>
              <option value="PENDING">Pending</option>
            </select>
          </div>

          <div className="space-y-1.5">
            <label className="block text-sm font-medium text-slate-700">Mentor Remarks & Feedback</label>
            <textarea 
              rows="2" 
              value={formData.remarks} 
              onChange={e => setFormData({...formData, remarks: e.target.value})} 
              className="w-full px-4 py-3 text-sm border border-slate-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-indigo-500/20 focus:border-indigo-500 transition-all placeholder:text-slate-400"
              placeholder="Provide constructive feedback for the student..."
            ></textarea>
          </div>

          <div className="pt-2 flex justify-end">
            <button 
              type="submit" 
              disabled={isSubmitting || !formData.studentId}
              className="w-full sm:w-auto px-6 py-2.5 bg-indigo-600 text-white text-sm font-medium rounded-lg hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-indigo-500 focus:ring-offset-2 transition-all disabled:opacity-70 flex items-center gap-2"
            >
              {isSubmitting ? <Loader2 className="w-4 h-4 animate-spin" /> : <UploadCloud className="w-4 h-4" />}
              {isSubmitting ? 'Submitting...' : 'Submit Progress'}
            </button>
          </div>
        </form>
      </div>

      {/* History Section */}
      <div className="bg-white rounded-2xl shadow-sm border border-slate-200 overflow-hidden">
        <div className="px-6 py-4 border-b border-slate-100 bg-slate-50/50 flex justify-between items-center">
          <div className="flex items-center gap-2">
            <History className="w-5 h-5 text-slate-400" />
            <h4 className="font-medium text-slate-800">Progress History</h4>
          </div>
          {formData.studentId && <Badge variant="indigo">Student ID: {formData.studentId}</Badge>}
        </div>
        
        <div className="overflow-x-auto">
          <table className="w-full text-left border-collapse">
            <thead>
              <tr className="bg-slate-50 border-b border-slate-200 text-slate-500 text-xs uppercase tracking-wider">
                <th className="px-6 py-3 font-semibold">Week</th>
                <th className="px-6 py-3 font-semibold">Task Description</th>
                <th className="px-6 py-3 font-semibold">Status</th>
                <th className="px-6 py-3 font-semibold">Remarks</th>
              </tr>
            </thead>
            <tbody className="divide-y divide-slate-100">
              {!formData.studentId ? (
                <tr><td colSpan="4" className="px-6 py-8 text-center text-sm text-slate-400">Enter a Student ID above to view history.</td></tr>
              ) : loadingHistory ? (
                <tr><td colSpan="4" className="px-6 py-8 text-center"><Loader2 className="w-5 h-5 animate-spin mx-auto text-indigo-500" /></td></tr>
              ) : history.length === 0 ? (
                <tr><td colSpan="4" className="px-6 py-8 text-center text-sm text-slate-400">No progress records found for this student.</td></tr>
              ) : (
                history.map(record => (
                  <tr key={record.id} className="hover:bg-slate-50/50 transition-colors">
                    <td className="px-6 py-3 text-sm text-slate-700 font-medium text-center">Wk {record.weekNumber}</td>
                    <td className="px-6 py-3 text-sm text-slate-800">{record.taskDescription}</td>
                    <td className="px-6 py-3 text-sm">{getStatusBadge(record.completionStatus)}</td>
                    <td className="px-6 py-3 text-sm text-slate-500">{record.remarks || '-'}</td>
                  </tr>
                ))
              )}
            </tbody>
          </table>
        </div>
      </div>
    </div>
  );
};

// --- Main Application / Dashboard Layout ---
export default function MentorDashboard() {
  const [activeTab, setActiveTab] = useState('students');
  const [toast, setToast] = useState(null);
  const [isCollapsed, setIsCollapsed] = useState(false);
  
  // Hardcoded mentor ID for testing.
  const mentorId = 1; 

  const showToast = (message, type = 'success') => {
    setToast({ message, type });
    setTimeout(() => setToast(null), 4000);
  };

  const getPageTitle = () => {
    switch(activeTab) {
      case 'students': return 'Student Management';
      case 'attendance': return 'Attendance Records';
      case 'progress': return 'Weekly Tracking';
      default: return 'Dashboard';
    }
  };

  const navItems = [
    { id: 'students', label: 'Students', icon: Users },
    { id: 'attendance', label: 'Mark Attendance', icon: CalendarCheck },
    { id: 'progress', label: 'Weekly Progress', icon: TrendingUp },
  ];

  return (
    <div className="flex h-screen bg-slate-50 text-slate-800 font-sans antialiased overflow-hidden selection:bg-indigo-100 selection:text-indigo-900">
      
      {/* Premium Toast Notification */}
      {toast && (
        <div className={`fixed bottom-6 right-6 px-4 py-3 rounded-lg shadow-xl border flex items-center gap-3 z-50 animate-in slide-in-from-bottom-5 fade-in duration-300 ${
          toast.type === 'error' 
            ? 'bg-white border-red-100 text-slate-800 shadow-red-500/10' 
            : 'bg-white border-slate-200 text-slate-800 shadow-emerald-500/10'
        }`}>
          {toast.type === 'error' ? <XCircle className="w-5 h-5 text-red-500" /> : <CheckCircle className="w-5 h-5 text-emerald-500" />}
          <span className="text-sm font-medium">{toast.message}</span>
        </div>
      )}

      {/* Light Collapsible Sidebar */}
      <aside className={`${isCollapsed ? 'w-20' : 'w-64'} bg-white text-slate-700 flex flex-col border-r border-slate-200 shrink-0 transition-all duration-300 ease-in-out relative z-20`}>
        <div className={`h-16 flex items-center ${isCollapsed ? 'justify-center' : 'gap-3 px-6'} border-b border-slate-200 bg-white`}>
          {!isCollapsed && <h1 className="text-lg font-bold text-slate-900 tracking-tight whitespace-nowrap overflow-hidden transition-all duration-300">Mentor Portal</h1>}
        </div>
        
        <div className="p-4 flex-1">
          {!isCollapsed && <p className="px-3 text-xs font-semibold text-slate-400 uppercase tracking-wider mb-2">Main Menu</p>}
          <nav className="space-y-1">
            {navItems.map(item => {
              const Icon = item.icon;
              const isActive = activeTab === item.id;
              return (
                <button 
                  key={item.id}
                  onClick={() => setActiveTab(item.id)} 
                  title={isCollapsed ? item.label : ''}
                  className={`w-full flex items-center ${isCollapsed ? 'justify-center px-0' : 'gap-3 px-3'} py-2.5 rounded-lg transition-all duration-200 text-sm font-medium
                    ${isActive 
                      ? 'bg-indigo-50 text-indigo-700' 
                      : 'text-slate-600 hover:bg-slate-100 hover:text-slate-900'
                    }`}
                >
                  <Icon className={`w-5 h-5 shrink-0 ${isActive ? 'text-indigo-600' : 'text-slate-400'}`} /> 
                  {!isCollapsed && <span className="whitespace-nowrap overflow-hidden">{item.label}</span>}
                </button>
              );
            })}
          </nav>
        </div>

        {/* User Profile Area in Sidebar */}
        <div className="p-4 border-t border-slate-200 bg-slate-50/50">
          <div className={`flex items-center ${isCollapsed ? 'justify-center' : 'gap-3 px-2'}`}>
            <div className="w-9 h-9 shrink-0 rounded-full bg-indigo-100 flex items-center justify-center text-indigo-700 text-sm font-bold border border-indigo-200">
              M
            </div>
            {!isCollapsed && (
              <div className="flex-1 min-w-0">
                <p className="text-sm font-medium text-slate-900 truncate">Mentor Account</p>
              </div>
            )}
          </div>
        </div>
      </aside>

      {/* Main Content Area */}
      <main className="flex-1 flex flex-col min-w-0 h-full overflow-hidden relative">
        <header className="h-16 bg-white border-b border-slate-200 px-4 sm:px-8 flex justify-between items-center shrink-0 z-10">
          <div className="flex items-center gap-4">
            <button 
              onClick={() => setIsCollapsed(!isCollapsed)} 
              className="p-2 text-slate-500 hover:text-slate-700 hover:bg-slate-100 rounded-lg transition-colors focus:outline-none"
            >
              <Menu className="w-5 h-5" />
            </button>
            <h2 className="text-xl font-semibold text-slate-800 tracking-tight">{getPageTitle()}</h2>
          </div>
          
          <div className="flex items-center gap-4">
            {/* Disabled UI icons per earlier cleanup */}
          </div>
        </header>

        {/* Dynamic Page Content */}
        <div className="flex-1 overflow-y-auto p-6 lg:p-10 bg-slate-50">
          <div className="max-w-6xl mx-auto">
            {activeTab === 'students' && <AssignedStudents mentorId={mentorId} />}
            {activeTab === 'attendance' && <AttendanceForm mentorId={mentorId} showToast={showToast} />}
            {activeTab === 'progress' && <ProgressForm mentorId={mentorId} showToast={showToast} />}
          </div>
        </div>
      </main>
    </div>
  );
}